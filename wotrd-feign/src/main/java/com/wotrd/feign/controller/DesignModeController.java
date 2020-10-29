package com.wotrd.feign.controller;

import com.wotrd.feign.designmode.chain.ValidateChainBuilder;
import com.wotrd.feign.designmode.strategy.BizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DesignModeController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/29 17:52
 */
@RestController
public class DesignModeController {

    @Autowired
    private BizService bizService;

    @Autowired
    private ValidateChainBuilder builder;

    @GetMapping("strategyMode")
    public void testStrategyMode(Integer type){
        bizService.makeOrder(type);
    }

    @GetMapping("check")
    public void checkChain(Integer type){
        builder.build().validate(type);
    }
}
