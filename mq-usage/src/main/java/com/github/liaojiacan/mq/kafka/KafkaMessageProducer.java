package com.github.liaojiacan.mq.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class KafkaMessageProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ListenableFuture<SendResult<String, String>> send(String topic, String key, String payload) {
		if (StringUtils.isEmpty(key)) {
			return kafkaTemplate.send(topic, null, payload);
		}
		return kafkaTemplate.send(topic, key, payload);
	}

}
