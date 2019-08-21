package com.wotrd.kafkatemplate.receiver;

import com.wotrd.kafkatemplate.domain.Fool;
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
 *
 */
@Slf4j
@Component
public class KafkaReceiver {

    /**
     * id是消费者的ID
     * topics可以监听多个topic，用逗号隔开
     * @param record
     */
    @KafkaListener(id = "id1", topics = {"kafka1"})
    public void receiver(ConsumerRecord<?, ?> record) {
        log.info("record={}", record);

    }

    @KafkaListener(topics = {"kafka1"})
    public void receiver1(String msg) {

        log.info("topic:{}:{}", "hahah", msg);
    }

    @KafkaListener(id = "fooGroup", topics = "topic1")
    public void listen(Fool foo) {
        log.info("Received: " + foo);
        if (foo.getFool().startsWith("fail")) {
            throw new RuntimeException("failed");
        }
    }

    @KafkaListener(id = "dltGroup", topics = "topic1.DLT")
    public void dltListen(String in) {
        log.info("Received from DLT: " + in);
    }


//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;

//    @KafkaListener(id = "fooGroup2", topics = "topic2")
//    public void listen1(List<Fool> foos) throws IOException {
//        log.info("Received: " + foos);
//        foos.forEach(f -> kafkaTemplate.send("topic3", f.getFool().toUpperCase()));
//        log.info("Messages sent, hit Enter to commit tx");
//        System.in.read();
//    }
//
//    @KafkaListener(id = "fooGroup3", topics = "topic3")
//    public void listen2(List<String> in) {
//        log.info("Received: " + in);
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


}
