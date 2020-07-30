package com.wotrd.dubboprovider.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class SyncKafkaDataService {


    @Autowired
    private KafkaTemplate template;

    private KafkaConsumer getConsumer(String group) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "175.24.76.13:9092");
        props.put("session.timeout.ms", 30000);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(props);
    }

    public void repaireKafkaGroupOffset(String topic, String targetTopic, String group, String start, String end) {
        KafkaConsumer consumer = null;
        try {
            if (StringUtils.isEmpty(group)) {
                group = "11read_group";
            }
            consumer = getConsumer(group);
            // 获取topic的partition信息
            List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
            List<TopicPartition> topicPartitions = new ArrayList<>();

            Map<TopicPartition, Long> timestampsToSearch = new HashMap<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = df.parse(start);
            // 计算30分钟之前的时间戳
            long fetchDataTime = startTime.getTime();
            //截止时间戳
            long endTime;
            if (StringUtils.isEmpty(end)) {
                endTime = System.currentTimeMillis();
            } else {
                endTime = df.parse(end).getTime();
            }
            for (PartitionInfo partitionInfo : partitionInfos) {
                topicPartitions.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
                timestampsToSearch.put(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()), fetchDataTime);
            }

            consumer.assign(topicPartitions);

            // 获取每个partition一个小时之前的偏移量
            Map<TopicPartition, OffsetAndTimestamp> map = consumer.offsetsForTimes(timestampsToSearch);

            OffsetAndTimestamp offsetTimestamp;
            log.info("开始设置各分区初始偏移量...");
            for (Map.Entry<TopicPartition, OffsetAndTimestamp> entry : map.entrySet()) {
                // 如果设置的查询偏移量的时间点大于最大的索引记录时间，那么value就为空
                offsetTimestamp = entry.getValue();
                if (offsetTimestamp != null) {
                    int partition = entry.getKey().partition();
                    long timestamp = offsetTimestamp.timestamp();
                    long offset = offsetTimestamp.offset();
                    log.info("partition = " + partition +
                            ", time = " + df.format(new Date(timestamp)) +
                            ", offset = " + offset);
                    // 设置读取消息的偏移量
                    consumer.seek(entry.getKey(), offset);
                }
            }
            log.info("设置各分区初始偏移量结束...");
            boolean result = true;
            while (result) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    log.info("转发开始partition = {}", record);

                    template.send(targetTopic, record.value());
                    long currentTime = record.timestamp();
                    if (currentTime > endTime + 1000 * 60) {
                        result = false;
                        break;
                    }
                }

            }
            log.info("数据转发完成");
            consumer.close();
        } catch (Exception e) {
            log.error("设置各分区初始偏移量失败", e);
            if (null != consumer) {
                consumer.close();
            }
        } finally {
            if (null != consumer) {
                consumer.close();
            }
        }

    }

}
