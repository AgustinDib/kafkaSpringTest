package com.prismamp.todopago.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.model.Greeting;

@Service
public class PublisherService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private KafkaTemplate<String, Greeting> greetingKafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send("kafkaMessage", msg);
	}

	public void sendGreeting(Greeting greeting) {
		greetingKafkaTemplate.send("kafkaGreeting", greeting);
	}
}
