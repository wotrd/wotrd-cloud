package com.wotrd.dubbo.common.designmode.flow;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:17
 */
public interface Processor<REQ, RES, CTX> {

    void process(FlowContext<REQ, RES, CTX> flowContext);

}
