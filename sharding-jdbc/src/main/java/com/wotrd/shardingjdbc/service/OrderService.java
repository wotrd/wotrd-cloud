package com.wotrd.shardingjdbc.service;

import com.wotrd.shardingjdbc.domain.Order;
import com.wotrd.shardingjdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Transactional
    public void insert(Order order) {

        orderMapper.insert(order);

        Long id = order.getId();
        log.info("Generated Key--id:" + id);
    }


    public void findAll() {
        List<Order> orders = orderMapper.queryAll();
        log.info("user:{}", orders);
        log.info("user:{}",orders.size());
    }


    public void getById(@RequestParam Long id) {
        Order order = orderMapper.queryById(id);
        log.info("user:{}", order);
    }


    public void getByUserId(@RequestParam Long userId) {
        List<Order> orders = orderMapper.queryByUserId(userId);
        log.info("user:{}", orders);
    }

    public void deleteById( Long id) {
        orderMapper.deleteById(id);
        log.info("user:{}", id);
    }


}
