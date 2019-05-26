package com.wotrd.sentinelservice.service;

import com.wotrd.sentinelservice.domain.Order;
import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.nacos.common.conf.GlobalResponse;
import com.wotrd.sentinelservice.service.fallback.ProviderFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "provider-service", fallback = ProviderFallbackService.class)
public interface IProviderService {

    @RequestMapping("provider/add")
    GlobalResponse addOrer(@RequestBody GlobalRequestBody<Order> body);

    @RequestMapping("provider/getOrders")
    GlobalResponse getOrders();


}
