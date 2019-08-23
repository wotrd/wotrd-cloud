package com.wotrd.kafkatemplate.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 需要修改server.properties的listener主机地址不然Java获取不到消息。
 * partitions和replacation和broker没有关系，和部署有关。
 * 可以指定partition进行发送
 * 配置consumer groupId同一个group只有一个监听者可以收到
 * kafka默认自动提交在broker断网或down机情况rebalance重平衡会造成，消息消费但是没有提交重复消费问题。
 * 可以结合事务处理，实现只消费一次。
 * 消费的时候，需要注意每次拉去的条数，还有拉去提交时间。防止，提交循环不成功。
 * 还可以使用多线程和多线程快速消费。
 * kafka有
 */
@Slf4j
@Component
public class KafkaReceiver {

    /**
     * id是消费者的ID
     * topics可以监听多个topic，用逗号隔开
     * groupId配置后，只有一个服务可以收到
     *
     * @param record
     */
    @KafkaListener(id = "consumer", topics = {"kafka"}, groupId = "marketGroup9")
    public void receiver(ConsumerRecord<?, ?> record, KafkaConsumer consumer) {
        log.info("record={}", record);

    }

    /**
     * id是消费者的ID
     * topicPartitions监听多个topic和每个topic的多个partition，
     * partitions和partitionOffsets不能同时使用，partitionOffsets可以设置初始偏移量
     *
     * @param record
     */
    @KafkaListener(id = "thing2", topicPartitions = {@TopicPartition(topic = "topic1", partitions = {"0", "1"}),
            @TopicPartition(topic = "topic2", partitions = "0"
                    /*, partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100")*/)})
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("record:{}", record);
    }

    /**
     * 使用ack手动确认模式
     *
     * @param data
     * @param ack
     */
    @KafkaListener(id = "cat", topics = "myTopic"/*,
            containerFactory = "kafkaManualAckListenerContainerFactory"*/)
    public void listen(String data, Acknowledgment ack) {
        log.info("data:{}", data);
        ack.acknowledge();
    }

    /**
     * 获取消息的header头信息
     *
     * @param foo
     * @param key
     * @param partition
     * @param topic
     * @param ts
     */
    @KafkaListener(id = "qux", topicPattern = "myTopic1")
    public void listen(@Payload String foo,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Integer key,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts) {
        log.info("fool:{}, keu:{}, partition:{}, topic:{}, timestamp:{}", foo, key, partition, topic, ts);
    }

    /**
     * 批处理
     *
     * @param list
     * @param keys
     * @param partitions
     * @param topics
     * @param offsets
     */
    @KafkaListener(id = "list", topics = "myTopic"/*, containerFactory = "batchFactory"*/)
    public void listen(List<String> list,
                       @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<Integer> keys,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                       @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("fool:{}, keu:{}, partition:{}, topic:{}, timestamp:{}", list, keys, partitions, topics, offsets);
    }

    /**
     * 自动添加topic到broker中，numPartitions配置partition的数量
     * replicationFactor配置备份的数量
     *
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
