package com.wotrd.dubbo.common.designmode.flow.checker;

import com.wotrd.dubbo.common.designmode.flow.Checker;
import com.wotrd.dubbo.common.designmode.flow.FlowContext;
import com.wotrd.dubbo.common.designmode.flow.context.CreateOrderContext;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:46
 */
@Slf4j
@Component
public class CreateOrderChecker implements Checker<CreateOrderRequest, CreateOrderResult, CreateOrderContext> {

    @Override
    public void check(FlowContext<CreateOrderRequest, CreateOrderResult, CreateOrderContext> flowContext) {
        CreateOrderRequest request = flowContext.getRequest();
        flowContext.getCtx().setRequest(request);
        log.info("check order param:{}", request);

    }
}
