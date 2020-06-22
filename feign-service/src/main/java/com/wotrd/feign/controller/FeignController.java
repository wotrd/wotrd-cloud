package com.wotrd.feign.controller;

import com.wotrd.feign.model.bo.Order;
import com.wotrd.feign.service.IProviderService;
import com.wotrd.feign.config.GlobalRequestBody;
import com.wotrd.feign.config.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("feign")
@RestController
public class FeignController {


    @Autowired
    private IProviderService providerService;

    @RequestMapping("add")
    public GlobalResponse add(@RequestBody @Valid GlobalRequestBody<Order> body){
        return providerService.addOrer(body);
    }

    @RequestMapping("getOrders")
    public GlobalResponse getOrders(){
        return providerService.getOrders();
    }

}
