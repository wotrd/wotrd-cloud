package com.wotrd.dubbo.common.designmode.interceptor;

import com.wotrd.dubbo.client.domain.Result;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/9 21:01
 */
public class InterceptorDemo {

    public static void main(String[] args) {
        InterceptorInvocation interceptorInvocation = new InterceptorInvocation();
        interceptorInvocation.addInterceptor(new LogInterceptor());
        interceptorInvocation.addInterceptor(new AuditInterceptor());
        interceptorInvocation.setRequest(new Request());
        interceptorInvocation.setTarget(new Target() {
            @Override
            public Result execute(Request request) {
                System.out.println("jhaj");
                return null;
            }
        });
        interceptorInvocation.invoke();
    }
}
