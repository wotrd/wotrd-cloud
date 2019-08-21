package com.wotrd.kafkatemplate.controller;

import com.alibaba.fastjson.JSONObject;
import com.wotrd.kafkatemplate.domain.Fool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

/**
 * 使用KafkaTemplate操作kafka
 * send需要加topic参数，sendDefault使用默认topic需要在应用配置文件中配置
 */
@Slf4j
@RequestMapping("kafka")
@RestController
public class KafkaController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    @RequestMapping("sendMsg")
    public String sendMsg(@RequestParam String msg) {

        Fool fool = Fool.builder().fool(msg).build();
        log.info("message={}", JSONObject.toJSONString(fool));
        //topic在发送时会自动进行创建
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("kafka1", JSONObject.toJSONString(fool));

        CompletableFuture<SendResult<String, String>> future1 = future.completable();
        boolean done = future1.isDone();

        return "success";

    }


}
