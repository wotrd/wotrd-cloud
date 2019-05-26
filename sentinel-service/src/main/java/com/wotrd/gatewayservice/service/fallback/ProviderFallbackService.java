package com.wotrd.gatewayservice.service.fallback;

import com.wotrd.nacos.common.conf.GlobalRequestBody;
import com.wotrd.nacos.common.conf.GlobalResponse;
import com.wotrd.gatewayservice.domain.Order;
import com.wotrd.gatewayservice.service.IProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProviderFallbackService implements IProviderService {

    @Override
    public GlobalResponse addOrer(GlobalRequestBody<Order> body) {
        return GlobalResponse.builder().code("500").msg("add order failed").build();
    }

    @Override
    public GlobalResponse getOrders() {
        log.error("get Orders failed");
        return GlobalResponse.builder().code("500").msg("get orders failed").build();
    }
}
