package com.wotrd.dubbo.common.designmode.interceptor;

import com.wotrd.dubbo.client.domain.Result;

/**
 * @description: 拦截目标对象
 * @Author: wotrd
 * @date: 2021/5/9 20:53
 */
public interface Target {

    Result execute(Request request);
}
