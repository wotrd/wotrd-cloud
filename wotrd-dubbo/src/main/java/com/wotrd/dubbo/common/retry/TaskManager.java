package com.wotrd.dubbo.common.retry;


import com.wotrd.dubbo.common.retry.base.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Date;

@Component
public class TaskManager {
    //  持久化到磁盘文件位置
    private String taskSaveLocation = "/tmp/project/retryTaskV2";

    private RetryTemplate retryTemplate;

    @Resource
    private WotrdRetryCallback retryCallback;
//    @Resource
//    private RetryFailCallback retryFailedCallBack;

    public TaskManager() {
    }

    @PostConstruct
    public void init() {
        this.retryTemplate = new RetryTemplate<>(retryCallback, new DefaultRetryPolicy<>(), this.taskSaveLocation, "wotrd_mq");
        this.retryTemplate.init();
    }

    public void addRetry(BaseTaskParam taskParam) {
        TaskContext<BaseTaskParam> taskContext = new TaskContext<>();
        taskContext.setCurExecuteCnt(1);
        taskContext.setNextExecuteTime(System.currentTimeMillis() + 5 * 1000);
        taskContext.setTaskParam(taskParam);
        this.retryTemplate.addTask(taskContext);
    }



    //重试策略
    public static class DefaultRetryPolicy<T extends Serializable> implements RetryPolicy<T> {
        private static int[] RETRY_DELAY_SECONDES = new int[]{1, 5, 15, 60, 360, 1800,3600};

        DefaultRetryPolicy() {
        }

        @Override
        public boolean isValid(TaskContext<T> taskContext) {
            return taskContext != null && taskContext.getCurExecuteCnt() <= RETRY_DELAY_SECONDES.length - 1;
        }

        @Override
        public boolean isReady(TaskContext<T> taskContext) {
            return taskContext.getNextExecuteTime() < (new Date()).getTime();
        }

        @Override
        public long getNextExecuteTime(TaskContext<T> taskContext) {
            long timeDelay = (long)(RETRY_DELAY_SECONDES[taskContext.getCurExecuteCnt()] * 1000);
            return (new Date()).getTime() + timeDelay;
        }
    }
}
