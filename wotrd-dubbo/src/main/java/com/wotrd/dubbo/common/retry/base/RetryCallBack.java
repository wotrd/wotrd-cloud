package com.wotrd.dubbo.common.retry.base;

import java.io.Serializable;

public interface RetryCallBack<T extends Serializable> {
    boolean doRetry(TaskContext<T> taskContext);
}
