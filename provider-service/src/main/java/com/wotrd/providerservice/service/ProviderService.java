package com.wotrd.providerservice.service;

import com.wotrd.providerservice.domain.Order;
import com.wotrd.providerservice.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProviderService {

    @Autowired
    private OrderMapper orderMapper;


    public Map<String, Object> getOrders() {
        Map<String,Object> returnMap = new HashMap<>();

        List<Order> orders = orderMapper.queryAll();
        returnMap.put("data", orders);
        return returnMap;
    }

    public Map<String, Object> add(Order order) {
        Map<String,Object> returnMap = new HashMap<>();

        int insert = orderMapper.insert(order);
        returnMap.put("result", "success");
        return returnMap;
    }
}
