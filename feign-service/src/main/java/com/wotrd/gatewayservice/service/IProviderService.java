package com.wotrd.gatewayservice.service;

import com.wotrd.gatewayservice.domain.Order;
import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.nacos.common.conf.GlobalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider-service")
public interface IProviderService {

    @RequestMapping("provider/add")
    GlobalResponse addOrer(@RequestBody GlobalRequestBody<Order> body);

    @RequestMapping("provider/getOrders")
    GlobalResponse getOrders();
}
