package com.wotrd.feign.service;

import com.wotrd.feign.factory.FeignServiceFactory;
import com.wotrd.feign.model.bo.Order;
import com.wotrd.feign.config.GlobalRequestBody;
import com.wotrd.feign.config.GlobalResponse;
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
