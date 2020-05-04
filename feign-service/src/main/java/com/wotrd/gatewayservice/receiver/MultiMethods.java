package com.wotrd.gatewayservice.receiver;

import com.wotrd.gatewayservice.domain.Bar;
import com.wotrd.gatewayservice.domain.Fool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@KafkaListener(id = "multiGroup", topics = { "foos", "bars" })
public class MultiMethods {

    @KafkaHandler
    public void foo(Fool foo) {
        log.info("Received: " + foo);
    }

    @KafkaHandler
    public void bar(Bar bar) {
        log.info("Received: " + bar);
    }

    @KafkaHandler(isDefault = true)
    public void unknown(Object object) {
        log.info("Received unknown: " + object);
    }

}