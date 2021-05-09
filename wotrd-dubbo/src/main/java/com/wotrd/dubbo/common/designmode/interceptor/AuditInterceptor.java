package com.wotrd.dubbo.common.designmode.interceptor;

import org.springframework.util.Assert;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/9 21:06
 */
public class AuditInterceptor implements Interceptor{
    @Override
    public void interceptor(InterceptorInvocation interceptorInvocation) {
        Assert.notNull(interceptorInvocation, "interceptorInvocation is null");
        Assert.notNull(interceptorInvocation.getTarget(), "interceptorInvocation.target is null");
        System.out.println("audit interceptor start");
        interceptorInvocation.invoke();
        System.out.println("audit interceptor end");
    }
}
