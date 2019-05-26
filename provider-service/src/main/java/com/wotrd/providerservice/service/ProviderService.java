package com.wotrd.providerservice.service;

import com.wotrd.nacos.common.conf.GlobalResponse;
import com.wotrd.providerservice.domain.Order;
import com.wotrd.providerservice.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

    @Autowired
    private OrderMapper orderMapper;


    public GlobalResponse getOrders() {
        List<Order> orders = orderMapper.queryAll();
        return GlobalResponse.builder().msg("success").code("200").data(orders).build();
    }

    public GlobalResponse add(Order order) {

        orderMapper.insert(order);
        return GlobalResponse.builder().msg("success").code("200").build();
    }
}
