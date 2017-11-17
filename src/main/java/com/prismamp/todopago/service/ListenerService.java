package com.prismamp.todopago.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.model.Greeting;

@Service
public class ListenerService {

	@KafkaListener(topics = "kafkaMessage")
	public void listenMessage(String message) {
		System.out.println("Se recibió el mensaje: " + message);
	}

	@KafkaListener(topics = "kafkaGreeting", containerFactory = "greetingKafkaListenerContainerFactory")
	public void greetingListener(Greeting greeting) {
		System.out.println("Se recibió el mensaje: " + greeting.getMsg());
		System.out.println("Se recibió el mensaje: " + greeting.getName());
	}
}
