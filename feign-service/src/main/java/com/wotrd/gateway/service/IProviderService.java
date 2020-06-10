package com.wotrd.gateway.service;

import com.wotrd.gateway.factory.FeignServiceFactory;
import com.wotrd.gateway.model.bo.Order;
import com.wotrd.gateway.config.GlobalRequestBody;
import com.wotrd.gateway.config.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider-service", fallbackFactory = FeignServiceFactory.class)
public interface IProviderService {

    @RequestMapping("provider/add")
    GlobalResponse addOrer(@RequestBody GlobalRequestBody<Order> body);

    @RequestMapping("provider/getOrders")
    GlobalResponse getOrders();
}
