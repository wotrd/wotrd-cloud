package com.wotrd.dubbo.common.designmode.flow;

import com.wotrd.dubbo.common.designmode.flow.checker.CreateOrderChecker;
import com.wotrd.dubbo.common.designmode.flow.context.CreateOrderContext;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import com.wotrd.dubbo.common.designmode.flow.processor.CreateOrderProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:43
 */
@Configuration
public class FlowContextConfig {

    @Resource
    private CreateOrderChecker createOrderChecker;

    @Resource
    private CreateOrderProcessor createOrderProcessor;

    @Bean
    public EngineFlow<CreateOrderRequest, CreateOrderResult, CreateOrderContext> createOrderFlow(){
        EngineFlow.EngineFlowBuilder builder = EngineFlow.EngineFlowBuilder.builder();
        return builder.exceptionHandler(e-> new CreateOrderResult())
                .checker(createOrderChecker)
                .processor(createOrderProcessor)
                .contextClass(CreateOrderContext.class)
                .build();
    }
}
