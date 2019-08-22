package com.wotrd.kafkatemplate.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 需要修改server.properties的listener主机地址不然Java获取不到消息。
 * partitions和replacation和broker没有关系，和部署有关。
 * 可以指定partition进行发送
 * listeners=PLAINTEXT://192.168.232.128:9092
 * 配置consumer groupId同一个group只有一个监听者可以收到
 */
@Slf4j
@Component
public class KafkaReceiver {

    /**
     * id是消费者的ID
     * topics可以监听多个topic，用逗号隔开
     * @param record
     */
    @KafkaListener(id = "consumer", topics = {"kafka"})
    public void receiver(ConsumerRecord<?, ?> record) {
        log.info("record={}", record);

    }

    /**
     * id是消费者的ID
     * topics可以监听多个topic，用逗号隔开
//     * @param record
     */
//    @KafkaListener(topicPartitions = {partitions={""}})
//    public void receiver1(String msg) {
//
//        log.info("topic:{}:{}", "hahah", msg);
//    }


    /**
     * 自动添加topic到broker中，numPartitions配置partition的数量
     * replicationFactor配置备份的数量
     * @return
     */
    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 2, (short) 1);
    }

    @Bean
    public NewTopic kafka() {
        return new NewTopic("kafka", 5, (short) 1);
    }



}
