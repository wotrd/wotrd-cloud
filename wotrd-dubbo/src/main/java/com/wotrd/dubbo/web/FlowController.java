package com.wotrd.dubbo.web;

import com.wotrd.dubbo.client.domain.Result;
import com.wotrd.dubbo.common.designmode.flow.EngineFlow;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/5 20:57
 */
@RequestMapping("flow")
@RestController
public class FlowController {

    @Resource
    private EngineFlow<CreateOrderRequest, CreateOrderResult, CreateOrderResult> createOrderFlow;

    @RequestMapping("createOrder")
    public Result createOrder(){
        CreateOrderRequest request = new CreateOrderRequest();
        CreateOrderResult result = createOrderFlow.start(request);
        return Result.buildSuccess(result);
    }
}
