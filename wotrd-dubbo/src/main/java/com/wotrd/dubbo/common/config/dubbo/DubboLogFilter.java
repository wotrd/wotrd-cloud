package com.wotrd.dubbo.common.config.dubbo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.*;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 23:22
 */
@Slf4j
public class DubboLogFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result = null;
        long start = System.currentTimeMillis();
        try {
            result = invoker.invoke(invocation);
            return result;
        } finally {
            long costTime = System.currentTimeMillis() - start;
            String interfaceName = invoker.getInterface().getCanonicalName();
            String method = interfaceName + "." + invocation.getMethodName();
            String args = JSON.toJSONString(invocation.getArguments());
//            Logger logger = CONSUMER_LOG;
            if (null != result && null != result.getException()) {
                log.info("invoke: {} ,param:{}，spend:{}ms，exception:{}", method, args, costTime, result.getException().getMessage());
            } else {
                String resultStr = null == result ? "" : JSON.toJSONString(result.getValue());
                log.info("invoke: {} ,param:{}, result:{}，spend: {} ms", method, args, resultStr, costTime);
            }
        }
    }
}
