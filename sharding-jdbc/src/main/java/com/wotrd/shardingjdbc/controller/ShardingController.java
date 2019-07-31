package com.wotrd.shardingjdbc.controller;

import com.wotrd.shardingjdbc.domain.Order;
import com.wotrd.shardingjdbc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("sharding")
@RestController
public class ShardingController {

    @Autowired
    private OrderService orderService;

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
