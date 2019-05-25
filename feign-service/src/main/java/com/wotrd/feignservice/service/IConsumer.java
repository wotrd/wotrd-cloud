package com.wotrd.feignservice.service;

import com.wotrd.feignservice.service.fallback.ConsumerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(serviceId = "provider-service", fallback = ConsumerFallback.class)
public interface IConsumer {

    @RequestMapping("/echo/{word}")
    String sayHello(@PathVariable("word") String word);

}
