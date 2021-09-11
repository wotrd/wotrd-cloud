package com.wotrd.dubbo.service.statemachine;

import com.alibaba.cola.statemachine.Action;
import com.alibaba.cola.statemachine.Condition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/6/6 23:16
 */
@Slf4j
@Component
public class State1To2Component {

    public Condition<StateContext> checkCondition(){
        return (StateContext context)-> {
            if (context.getId() == 1){
        return true;
    }
            return false;
        };
    }

    public Action<StateEnum, EventEnum, StateContext> doAction(){
        return (from, to, event, context)->{
            log.info("from, to, event, context:{}, {}, {}, {}", from, to, event, context);
        };
    }

}
