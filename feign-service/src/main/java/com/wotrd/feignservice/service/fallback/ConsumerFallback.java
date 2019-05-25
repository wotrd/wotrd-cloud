package com.wotrd.feignservice.service.fallback;

import org.springframework.stereotype.Component;

@Component
public class ConsumerFallback {

    public String sayHello(String word){
        return "server is missing,please wait a few!";
    }
}
