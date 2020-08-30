package com.wotrd.sharding.controller;

import com.wotrd.sharding.domain.Config;
import com.wotrd.sharding.domain.Order;
import com.wotrd.sharding.mapper.ConfigMapper;
import com.wotrd.sharding.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequestMapping("sharding")
@RestController
public class Controller {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ConfigMapper configMapper;

    @RequestMapping
    public String helloShardin() {
        return "hello Sharding-jdbc";
    }


    @RequestMapping("insert")
    public void insert(@RequestParam Integer orderId, @RequestParam Integer userId) {
        Order order = Order.builder()
                .orderId(orderId).userId(userId).build();
        orderService.insert(order);
    }


    @RequestMapping("queryAll")
    public void findAll() {
        orderService.findAll();
        List<Config> configs = configMapper.selectAll();
        log.info("configs{}", configs);
    }

    @RequestMapping("getById")
    public void getById(@RequestParam Long id) {
        orderService.getById(id);
    }

    @RequestMapping("getByUserId")
    public void getByUserId(@RequestParam Long userId) {
        orderService.getByUserId(userId);
    }

    @RequestMapping("deleteById")
    public void deleteById(@RequestParam Long id) {
        orderService.deleteById(id);
    }

}
