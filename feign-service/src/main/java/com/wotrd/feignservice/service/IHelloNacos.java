package com.wotrd.feignservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "provider-service")
public interface IHelloNacos {

    @RequestMapping("helloNacos")
    String helloNacos();

    @RequestMapping("echo/{word}")
    String echo(@PathVariable("word") String word);
}
