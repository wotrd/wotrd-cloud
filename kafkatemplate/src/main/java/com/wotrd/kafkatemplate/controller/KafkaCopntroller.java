package com.wotrd.kafkatemplate.controller;

import com.wotrd.kafkatemplate.utils.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("kafka")
@RestController
public class KafkaCopntroller {

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping("sendMsg")
    public String sendMsg(){

        kafkaSender.send();
        return "";
    }
}
