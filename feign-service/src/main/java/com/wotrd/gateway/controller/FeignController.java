package com.wotrd.gateway.controller;

import com.wotrd.gateway.model.bo.Order;
import com.wotrd.gateway.service.IProviderService;
import com.wotrd.gateway.config.GlobalRequestBody;
import com.wotrd.gateway.config.GlobalResponse;
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
