package com.wotrd.feignservice.controller;

import com.wotrd.feignservice.service.IConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private IConsumer consumer;

    @RequestMapping("say/{word}")
    public String sayHello(@PathVariable("word")String word){
        return "feign consumer:  "+consumer.sayHello(word);

    }
}
