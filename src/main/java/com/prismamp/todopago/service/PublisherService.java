package com.prismamp.todopago.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PublisherService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	private String topicName = "kafkaTest";

	public void sendMessage(String msg) {
		kafkaTemplate.send(topicName, msg);
	}
}
