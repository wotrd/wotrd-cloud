package com.wotrd.feign.config.retry;

import java.io.Serializable;

public interface RetryCallBack<T extends Serializable> {
    boolean doRetry(TaskContext<T> taskContext);
}
