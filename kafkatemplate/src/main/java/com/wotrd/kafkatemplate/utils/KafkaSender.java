package com.wotrd.kafkatemplate.utils;

import com.alibaba.fastjson.JSONObject;
import com.wotrd.kafkatemplate.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class KafkaSender{

    @Resource
    private KafkaTemplate<Integer,String> kafkaTemplate;

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("message={}", JSONObject.toJSONString(message));
        //topic在发送时会自动进行创建
        kafkaTemplate.send("kafka1", JSONObject.toJSONString(message));

    }


}
