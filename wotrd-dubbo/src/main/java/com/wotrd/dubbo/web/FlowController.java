package com.wotrd.dubbo.web;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;
import com.wotrd.dubbo.client.domain.Result;
import com.wotrd.dubbo.common.designmode.flow.EngineFlow;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderRequest;
import com.wotrd.dubbo.common.designmode.flow.domain.CreateOrderResult;
import com.wotrd.dubbo.service.statemachine.EventEnum;
import com.wotrd.dubbo.service.statemachine.MachineConstant;
import com.wotrd.dubbo.service.statemachine.StateContext;
import com.wotrd.dubbo.service.statemachine.StateEnum;
import org.frameworkset.util.Assert;
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

    @RequestMapping("testMachine")
    public Result testMachine(){
        StateMachine<StateEnum, EventEnum, StateContext> stateMachine = StateMachineFactory.get(MachineConstant.MACHINE_1);
        stateMachine.showStateMachine();
        StateContext context = new StateContext();
        context.setId(1L);
        context.setName("hahh");
        StateEnum stateEnum = stateMachine.fireEvent(StateEnum.STATE1, EventEnum.EVENT1, context);
        return Result.buildSuccess(stateEnum == StateEnum.STATE2);
    }

}
