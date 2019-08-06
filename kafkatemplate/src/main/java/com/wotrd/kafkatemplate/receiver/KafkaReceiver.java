package com.wotrd.kafkatemplate.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


//需要修改server.properties的listener主机地址不然Java获取不到消息。
//listeners=PLAINTEXT://192.168.232.128:9092

@Slf4j
@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"kafka1"})
    public void receiver(ConsumerRecord<?, ?> record) {
        log.info("record={}", record);

    }

    @KafkaListener(topics = {"kafka1"})
    public void receiver1(String msg) {

        log.info("topic:{}:{}", "hahah", msg);
    }

}
