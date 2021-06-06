package com.wotrd.dubbo.service.extension;

import com.alibaba.cola.extension.Extension;
import com.wotrd.dubbo.common.designmode.flow.FlowContext;
import com.wotrd.dubbo.common.designmode.flow.ProcessorExtPt;
import com.wotrd.dubbo.common.designmode.flow.context.CreateOrderContext;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;


/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/6 14:53
 */
@Slf4j
@Extension(bizId = BizConstant.BIZ_ONE, useCase = BizConstant.USER_CASE_ONE)
public class BizOneConstructOrderProcessorExtPt implements ProcessorExtPt<CreateOrderRequest, CreateOrderResult, CreateOrderContext> {

    @Override
    public void process(FlowContext flowContext) {
        log.info("bizOne process");
    }
}
