package com.wotrd.dubbo.common.retry.base;


import java.io.Serializable;

public interface RetryFailCallback<T extends Serializable> {
    boolean invoke(TaskContext<T> taskContext);
}
