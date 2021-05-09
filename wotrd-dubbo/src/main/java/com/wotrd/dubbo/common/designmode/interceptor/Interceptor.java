package com.wotrd.dubbo.common.designmode.interceptor;


/**
 * @description:拦截器接口
 * @Author: wotrd
 * @date: 2021/5/9 20:54
 */
public interface Interceptor {

    void interceptor(InterceptorInvocation interceptorInvocation);

}
