package com.wotrd.kafkatemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 使用KafkaTemplate操作kafka
 * send需要加topic参数，sendDefault使用默认topic需要在应用配置文件中配置
 * topic在发送时会自动进行创建
 */
@Slf4j
@RequestMapping("kafka")
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * 异步发送消息
     *
     * @param topic
     * @param partition
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("asyncSendMsgWithTopic")
    public String asyncSendMsgWithTopic(@RequestParam String topic, @RequestParam int partition, @RequestParam String key,
                                        @RequestParam String value) {
        final String[] result = new String[1];
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, partition, key, value);
        future.addCallback((SendResult<String, String> sendResult) -> {
            // todo success deal
            result[0] = "success";
        }, (Throwable ex) -> {
            // todo error deal
            result[0] = "failed";
        });
        return result[0];
    }

    /**
     * 同步发送消息,最大等待时间10秒钟
     *
     * @param topic
     * @param partition
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("syncSendMsgWithTopic")
    public String syncSendMsgWithTopic(@RequestParam String topic, @RequestParam int partition, @RequestParam String key,
                                       @RequestParam String value) {
        try {
            SendResult<String, String> result = kafkaTemplate.send(topic, partition, key, value).get(10, TimeUnit.SECONDS);
            return "success";
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        } catch (TimeoutException e) {
            log.error(e.getMessage(), e);
        }
        return "failed";
    }

    /**
     * 实现事务
     * 1Spring可以使用@Transactional实现
     * 2KafkaTemplate实现
     *
     * @param topic
     * @return
     */
    @RequestMapping("executeTransaction")
    public boolean executeTransaction(@RequestParam String topic) {
        boolean result = kafkaTemplate.executeInTransaction((KafkaOperations<String, String> operations) -> {
            operations.sendDefault("1");
            operations.sendDefault("2");
            return true;
        });
        log.info("topic:{} partitionInfos{}", topic, result);
        return result;

    }

    /**
     * 获取partitioninfo
     *
     * @param topic
     * @return
     */
    @RequestMapping("getPartitionInfos")
    public String getPartitionInfos(@RequestParam String topic) {

        List<PartitionInfo> partitionInfos = kafkaTemplate.partitionsFor(topic);
        log.info("topic:{} partitionInfos{}", topic, partitionInfos);
        return partitionInfos.toString();

    }


}
