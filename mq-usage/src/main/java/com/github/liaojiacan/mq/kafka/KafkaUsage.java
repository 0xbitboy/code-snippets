package com.github.liaojiacan.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.UUID;

/**
 * @author liaojiacan
 * @date 2019/8/19
 * @desc
 */
@Service
public class KafkaUsage {

    private Logger logger = LoggerFactory.getLogger(KafkaUsage.class);

    private final static String TOPIC = "hello-kafka";

    @Autowired
    private KafkaMessageProducer producer;


    public void sendMessage(String body){
        ListenableFuture<SendResult<String, String>> feature = producer.send(TOPIC, UUID.randomUUID().toString(), body);
        feature.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", throwable, TOPIC, body);
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                logger.info("kafka sendMessage success topic = {}, data = {}",TOPIC, body);
            }
        });
    }

    @KafkaListener(topics = TOPIC, groupId = "usage")
    public void processMessage(ConsumerRecord<Integer, String> record) {

        logger.info("processMessage, topic = {}, partition = {},offset = {}, msg = {}", record.topic(), record.partition(), record.offset(),record.value());
    }

}
