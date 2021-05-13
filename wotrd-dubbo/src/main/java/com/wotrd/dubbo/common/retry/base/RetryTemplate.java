package com.wotrd.dubbo.common.retry.base;

import com.wotrd.dubbo.common.retry.queue.BdbQueue;
import com.wotrd.dubbo.common.retry.queue.BdbQueueFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

public class RetryTemplate<T extends Serializable> {
    private BdbQueue<TaskContext<T>> taskQueue;
    private volatile boolean running = true;
    private AtomicBoolean isInited = new AtomicBoolean(false);
    private final RetryCallBack<T> retryCallBack;
    private final RetryPolicy<T> retryPolicy;
    private final String envPath;
    private final String queueName;
    private final Object syncObj = new Object();
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public RetryTemplate(RetryCallBack<T> retryCallBack, RetryPolicy<T> retryPolicy, String envPath, String queueName) {
        if (!StringUtils.isEmpty(envPath) && !StringUtils.isEmpty(queueName)) {
            if (null != retryCallBack && null != retryPolicy) {
                this.retryPolicy = retryPolicy;
                this.retryCallBack = retryCallBack;
                this.envPath = envPath;
                this.queueName = queueName;
            } else {
                throw new IllegalArgumentException("retryCallBack or retryCallBack should not be null");
            }
        } else {
            throw new IllegalArgumentException("envPath or queueName should not be null");
        }
    }

    public void init() {
        if (!this.isInited.compareAndSet(false, true)) {
            this.logger.info("{} has already inited", this.getClass());
        } else {
            this.taskQueue = BdbQueueFactory.getQueue(this.envPath, this.queueName);
            Thread retryThread = new Thread(new RetryTemplate.RetryWorker());
            retryThread.setDaemon(true);
            retryThread.setName(this.queueName);
            retryThread.start();
            this.registerShutdownHook();
        }
    }

    private void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> RetryTemplate.this.running = false));
    }

    private void tryAgainWhenFailed(TaskContext<T> taskContext) {
        long nextExecuteTime = this.retryPolicy.getNextExecuteTime(taskContext);
        taskContext.setNextExecuteTime(nextExecuteTime);
        taskContext.setCurExecuteCnt(taskContext.getCurExecuteCnt() + 1);
        this.addTask(taskContext);
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException var4) {
            this.logger.error("中断异常", var4);
        }

    }

    private TaskContext<T> waitUntilGetTask() {
        synchronized(this.syncObj) {
            TaskContext taskPacket;
            while((taskPacket = this.taskQueue.poll()) == null) {
                try {
                    this.syncObj.wait();
                } catch (InterruptedException var5) {
                    this.logger.error("中断异常", var5);
                }
            }

            return taskPacket;
        }
    }

    public void addTask(TaskContext<T> taskPacket) {
        if (null == taskPacket) {
            throw new IllegalArgumentException("taskPacket should not be null");
        } else {
            synchronized(this.syncObj) {
                this.taskQueue.offer(taskPacket);
                this.syncObj.notify();
            }
        }
    }

    private class RetryWorker implements Runnable {
        private RetryWorker() {
        }

        public void run() {
            while(RetryTemplate.this.running) {
                TaskContext<T> taskContext = RetryTemplate.this.waitUntilGetTask();
                if (!RetryTemplate.this.retryPolicy.isValid(taskContext)) {
                    RetryTemplate.this.logger.info("taskContext:{} is inValid, ignore", taskContext);
                } else if (!RetryTemplate.this.retryPolicy.isReady(taskContext)) {
                    RetryTemplate.this.addTask(taskContext);
                    RetryTemplate.this.sleep(100L);
                } else {
                    try {
                        boolean isRetrySuc = RetryTemplate.this.retryCallBack.doRetry(taskContext);
                        RetryTemplate.this.logger.info("执行任务,结果:{} 任务现场:{}", isRetrySuc, taskContext);
                        if (!isRetrySuc) {
                            RetryTemplate.this.tryAgainWhenFailed(taskContext);
                            RetryTemplate.this.logger.info("任务执行失败,重新加入队列,任务现场为:{}", taskContext);
                        }
                    } catch (Exception var3) {
                        RetryTemplate.this.logger.error("执行重试任务失败, 任务现场:{}", taskContext, var3);
                        RetryTemplate.this.tryAgainWhenFailed(taskContext);
                        RetryTemplate.this.logger.info("任务执行异常,重新加入队列,任务现场为:{}", taskContext);
                    }
                }
            }

        }
    }
}
