package com.wotrd.dubbo.common.designmode.flow;

import com.alibaba.cola.extension.ExtensionPointI;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:17
 */
public interface ProcessorExtPt<REQ, RES, CTX> extends ExtensionPointI {

    void process(FlowContext<REQ, RES, CTX> flowContext);

}
