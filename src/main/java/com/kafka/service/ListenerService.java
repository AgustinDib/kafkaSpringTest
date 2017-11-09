package com.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerService {

	@KafkaListener(topics = "kafkaTest")
	public void listenMessage(String message) {
		System.out.println("Se recibió el mensaje: " + message);
	}
}
