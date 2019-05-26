package com.wotrd.providerservice.controller;

import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.nacos.common.conf.GlobalResponse;
import com.wotrd.providerservice.domain.Order;
import com.wotrd.providerservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("provider")
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping("add")
    public GlobalResponse add(@RequestBody @Valid GlobalRequestBody<Order> body) {

        return providerService.add(body.getB());
    }

    @RequestMapping("getOrders")
    public GlobalResponse getOrders() {
        return providerService.getOrders();
    }

}
