package com.wotrd.providerservice.controller;

import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.providerservice.domain.Order;
import com.wotrd.providerservice.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequestMapping("provider")
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping("add")
    public Map<String, Object> add(@RequestBody @Valid GlobalRequestBody<Order> body) {

        return providerService.add(body.getB());
    }

    @RequestMapping("getOrders")
    public Map<String, Object> getOrders() {
        return providerService.getOrders();
    }

}
