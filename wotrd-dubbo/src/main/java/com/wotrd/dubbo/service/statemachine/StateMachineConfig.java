package com.wotrd.dubbo.service.statemachine;

import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/6 23:03
 */
@Configuration
public class StateMachineConfig {

    @Resource
    private State1To2Component state1To2Component;

    @PostConstruct
    public void initMachine(){
        StateMachineBuilder<StateEnum, EventEnum, StateContext> builder = StateMachineBuilderFactory.create();

        builder.externalTransition()
                .from(StateEnum.STATE1)
                .to(StateEnum.STATE2)
                .on(EventEnum.EVENT1)
                .when(state1To2Component.checkCondition())
                .perform(state1To2Component.doAction());
        builder.build(MachineConstant.MACHINE_1);
    }


}
