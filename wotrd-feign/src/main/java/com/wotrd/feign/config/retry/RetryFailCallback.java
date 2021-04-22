package com.wotrd.feign.config.retry;

import java.io.Serializable;

public interface RetryFailCallback<T extends Serializable> {
    boolean invoke(TaskContext<T> taskContext);
}
