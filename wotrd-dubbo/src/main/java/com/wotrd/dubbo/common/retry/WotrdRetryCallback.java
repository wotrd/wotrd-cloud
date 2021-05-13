package com.wotrd.dubbo.common.retry;

import com.wotrd.dubbo.common.retry.base.BaseTaskParam;
import com.wotrd.dubbo.common.retry.base.RetryCallBack;
import com.wotrd.dubbo.common.retry.base.TaskContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WotrdRetryCallback implements RetryCallBack<BaseTaskParam> {


    @Override
    public boolean doRetry(TaskContext<BaseTaskParam> taskContext) {
        BaseTaskParam taskParam = taskContext.getTaskParam();
        // todo 业务操作，false会继续重试
        log.info("execute business");
       return false;
    }

}
