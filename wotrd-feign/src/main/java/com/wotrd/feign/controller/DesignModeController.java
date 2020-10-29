package com.wotrd.feign.controller;

import com.wotrd.feign.designmode.BizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("strategyMode")
    public void testStrategyMode(Integer type){
        bizService.makeOrder(type);
    }
}
