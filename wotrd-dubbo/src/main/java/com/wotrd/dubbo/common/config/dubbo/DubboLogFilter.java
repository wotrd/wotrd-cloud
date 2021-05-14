package com.wotrd.dubbo.common.config.dubbo;

import com.alibaba.fastjson.JSON;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 23:22
 */
@Configuration
public class DubboLogFilter {

    @Bean
    public LogFilter logFilter(){
        return new LogFilter();
    }

    @Activate
    static class LogFilter implements Filter {
        private static final String OWN_PACKAGE = "com.wotrd.dubbo";

        private static final Logger CONSUMER_LOG = LoggerFactory.getLogger("consumer");

        private static final Logger PROVIDER_LOG = LoggerFactory.getLogger("provider");

        private static final Logger COMMON_LOG = LoggerFactory.getLogger(DubboLogFilter.class);

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
                Logger logger = CONSUMER_LOG;
                if (null != result && null != result.getException()) {
                    logger.info("invoke: {} ,param:{}，spend:{}ms，exception:{}", method, args, costTime, result.getException().getMessage());
                } else {
                    String resultStr = null == result ? "" : JSON.toJSONString(result.getValue());
                    logger.info("invoke: {} ,param:{}, result:{}，spend: {} ms", method, args, resultStr, costTime);
                }
            }
        }
    }
}
