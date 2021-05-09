package com.wotrd.dubbo.common.designmode.interceptor;


import org.springframework.util.Assert;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/9 21:01
 */
public class LogInterceptor implements Interceptor{

    @Override
    public void interceptor(InterceptorInvocation interceptorInvocation) {
        Assert.notNull(interceptorInvocation, "interceptorInvocation is null");
        Assert.notNull(interceptorInvocation.getTarget(), "interceptorInvocation.target is null");
        System.out.println("log interceptor start");
        interceptorInvocation.invoke();
        System.out.println("log interceptor end");
    }
}
