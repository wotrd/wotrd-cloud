package com.wotrd.sentinelservice.controller;

import com.wotrd.sentinelservice.domain.Order;
import com.wotrd.sentinelservice.service.IProviderService;
import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.nacos.common.conf.GlobalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequestMapping("sentinel")
@RestController
public class SentinelController {


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
