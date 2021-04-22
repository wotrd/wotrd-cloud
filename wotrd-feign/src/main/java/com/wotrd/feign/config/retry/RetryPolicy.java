package com.wotrd.feign.config.retry;

import java.io.Serializable;
import java.util.Date;

public interface RetryPolicy<T extends Serializable> {
    boolean isValid(TaskContext<T> var1);

    boolean isReady(TaskContext<T> var1);

    long getNextExecuteTime(TaskContext<T> var1);

    class DefaultDescending<T extends Serializable> implements RetryPolicy<T> {
        private static int[] RETRY_DELAY_MINS = new int[]{1, 2, 4, 8, 16, 32, 64, 128};

        public DefaultDescending() {
        }

        public boolean isValid(TaskContext<T> taskContext) {
            return taskContext != null && taskContext.getCurExecuteCnt() < RETRY_DELAY_MINS.length - 1;
        }

        public boolean isReady(TaskContext<T> taskContext) {
            return taskContext.getNextExecuteTime() < (new Date()).getTime();
        }

        public long getNextExecuteTime(TaskContext<T> taskContext) {
            long timeDelay = (long)(RETRY_DELAY_MINS[taskContext.getCurExecuteCnt()] * 1000 * 60);
            return (new Date()).getTime() + timeDelay;
        }
    }
}
