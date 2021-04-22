package com.wotrd.feign.config.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WotrdRetryCallback implements RetryCallBack<BaseTaskParam> {


    @Override
    public boolean doRetry(TaskContext<BaseTaskParam> taskContext) {
        BaseTaskParam taskParam = taskContext.getTaskParam();
       return false;
    }

}
