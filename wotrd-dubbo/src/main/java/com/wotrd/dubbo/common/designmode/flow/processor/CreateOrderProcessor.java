package com.wotrd.dubbo.common.designmode.flow.processor;

import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import com.wotrd.dubbo.common.designmode.flow.FlowContext;
import com.wotrd.dubbo.common.designmode.flow.ProcessorExtPt;
import com.wotrd.dubbo.common.designmode.flow.context.CreateOrderContext;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import com.wotrd.dubbo.service.extension.BizConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:48
 */
@Slf4j
@Component
public class CreateOrderProcessor implements ProcessorExtPt<CreateOrderRequest, CreateOrderResult, CreateOrderContext> {

    @Resource
    private ExtensionExecutor executor;

    @Override
    public void process(FlowContext<CreateOrderRequest, CreateOrderResult, CreateOrderContext> flowContext) {
        CreateOrderResult result = new CreateOrderResult();
        result.setOrderId(2L);
        result.setGmtCreate(new Date());
        result.setName("苹果");
        result.setTotalPrice(new BigDecimal("100"));
        flowContext.setResult(result);
        //        BizScenario bizScenario = BizScenario.newDefault();
        BizScenario bizScenario = BizScenario.valueOf(BizConstant.BIZ_ONE, BizConstant.USER_CASE_ONE);
        executor.executeVoid(ProcessorExtPt.class, bizScenario, e -> e.process(flowContext));
        log.info("create order process result:{}", result);
    }
}
