package com.wotrd.dubbo.common.designmode.flow.processor;

import com.wotrd.dubbo.common.designmode.flow.FlowContext;
import com.wotrd.dubbo.common.designmode.flow.Processor;
import com.wotrd.dubbo.common.designmode.flow.context.CreateOrderContext;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:48
 */
@Slf4j
@Component
public class CreateOrderProcessor implements Processor<CreateOrderRequest, CreateOrderResult, CreateOrderContext> {

    @Override
    public void process(FlowContext<CreateOrderRequest, CreateOrderResult, CreateOrderContext> flowContext) {
        CreateOrderResult result = new CreateOrderResult();
        result.setOrderId(2L);
        result.setGmtCreate(new Date());
        result.setName("苹果");
        result.setTotalPrice(new BigDecimal("100"));
        flowContext.setResult(result);
        log.info("create order process result:{}", result);
    }
}
