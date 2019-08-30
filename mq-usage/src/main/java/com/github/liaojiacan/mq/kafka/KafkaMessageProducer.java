package com.huya.hcg.message.kafka;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

public class KafkaMessageProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public ListenableFuture<SendResult<String, String>> send(String topic, String key, String payload) {
		if (StringUtils.isBlank(key)) {
			return kafkaTemplate.send(topic, null, payload);
		}
		return kafkaTemplate.send(topic, key, payload);
	}

}
