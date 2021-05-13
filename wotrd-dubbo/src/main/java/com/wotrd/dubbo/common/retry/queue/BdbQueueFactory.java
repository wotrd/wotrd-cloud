package com.wotrd.dubbo.common.retry.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 19:24
 */
public class BdbQueueFactory {
    private static ConcurrentHashMap<String, BdbEnvironment> bdbEnvironments = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Map<String, BdbQueue>> env2QueuesMap = new ConcurrentHashMap<>();
    private static final Object staticSyncObj = new Object();
    private static Logger logger = LoggerFactory.getLogger(BdbQueueFactory.class);

    public BdbQueueFactory() {
    }

    public static BdbQueue getQueue(String envPath, String queueName) {
        Map<String, BdbQueue> queueMap = env2QueuesMap.get(envPath);
        if (null != queueMap) {
            BdbQueue queue = queueMap.get(queueName);
            if (null != queue) {
                return queue;
            }
        }

        synchronized(staticSyncObj) {
            queueMap = env2QueuesMap.get(envPath);
            if (null != queueMap) {
                BdbQueue queue = queueMap.get(queueName);
                if (null != queue) {
                    return queue;
                }
            }

            BdbEnvironment bdbEnvironment = bdbEnvironments.get(envPath);
            if (bdbEnvironment == null) {
                bdbEnvironment = new BdbEnvironment(envPath);
                bdbEnvironments.put(envPath, bdbEnvironment);
                env2QueuesMap.put(envPath, new ConcurrentHashMap());
            }

            BdbQueue queue = new BdbQueue(bdbEnvironment, queueName);
            (env2QueuesMap.get(envPath)).put(queueName, queue);
            return queue;
        }
    }

    private static void closeAll() {
        Iterator<Map.Entry<String, Map<String, BdbQueue>>> var0 = env2QueuesMap.entrySet().iterator();

        while(var0.hasNext()) {
            Map.Entry<String, Map<String, BdbQueue>> queueMapEntry = var0.next();
            Map<String, BdbQueue> queueMap = queueMapEntry.getValue();

            for (Map.Entry<String, BdbQueue> tmp : queueMap.entrySet()) {
                String queueName = tmp.getKey();
                BdbQueue queue = tmp.getValue();

                try {
                    queue.close();
                    logger.info("queue:{} close success", queueName);
                } catch (Exception var8) {
                    logger.error("queue:{} close error ", queueName, var8);
                }
            }

            String envPath = queueMapEntry.getKey();
            BdbEnvironment environment = bdbEnvironments.get(envPath);

            try {
                environment.close();
                logger.info("environment path:{} close success", envPath);
            } catch (Exception var9) {
                logger.error("environment path:{} close error", envPath, var9);
            }
        }

    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                BdbQueueFactory.closeAll();
            }
        });
    }
}
