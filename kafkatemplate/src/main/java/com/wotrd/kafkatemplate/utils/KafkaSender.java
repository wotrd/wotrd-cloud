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
public class KafkaSender {



    //发送消息方法
    public void send(String text) {


    }
}
