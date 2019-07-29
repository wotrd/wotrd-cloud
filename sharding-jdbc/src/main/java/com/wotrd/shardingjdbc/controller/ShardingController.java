package com.wotrd.shardingjdbc.controller;

import com.wotrd.shardingjdbc.domain.Order;
import com.wotrd.shardingjdbc.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("sharding")
@RestController
public class ShardingController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping
    public String helloShardin(){
        return "hello Sharding-jdbc";
    }


    @RequestMapping("insert")
    public void insert(@RequestParam Integer orderId, @RequestParam Integer userId) {
        Order order = Order.builder()
                .orderId(orderId).userId(userId).build();

        orderMapper.insert(order);

        Long id = order.getId();
        log.info("Generated Key--id:" + id);
    }

    @RequestMapping("queryAll")
    public void findAll() {
        List<Order> orders = orderMapper.queryAll();
        log.info("user:{}", orders);
        log.info("user:{}",orders.size());
    }

    @RequestMapping("getById")
    public void getById(@RequestParam Long id) {
        Order order = orderMapper.queryById(id);
        log.info("user:{}", order);
    }

    @RequestMapping("getByUserId")
    public void getByUserId(@RequestParam Long userId) {
        List<Order> orders = orderMapper.queryByUserId(userId);
        log.info("user:{}", orders);
    }

    @RequestMapping("deleteById")
    public void deleteById(@RequestParam Long id) {
            orderMapper.deleteById(id);
        log.info("user:{}", id);
    }

}
