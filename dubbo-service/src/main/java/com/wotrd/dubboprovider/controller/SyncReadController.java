package com.wotrd.dubboprovider.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncReadController {

    @Autowired
    private SyncKafkaDataService syncKafkaDataService;

    @GetMapping("transferKafkaTopicData")
    public String transferKafkaTopicData(String topic, String targetTopic, String groupId, String start, String end) {
        syncKafkaDataService.repaireKafkaGroupOffset(topic, targetTopic, groupId, start, end);
        return "success";
    }

}
