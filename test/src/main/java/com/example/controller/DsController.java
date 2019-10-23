package com.example.controller;

import com.example.first.domain.Order;
import com.example.first.mapper.FirstMapper;
import com.example.second.mapper.SecondMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DsController {

    @Autowired
    private FirstMapper firstMapper;

    @Autowired
    private SecondMapper secondMapper;

    @RequestMapping("ds1")
    public String ds1(){
        Order order = firstMapper.getOrder();
        return "success";
    }

    @RequestMapping("ds2")
    public String ds2(){
        com.example.second.domain.Order order = secondMapper.getOrder();
        return "success";
    }
}
