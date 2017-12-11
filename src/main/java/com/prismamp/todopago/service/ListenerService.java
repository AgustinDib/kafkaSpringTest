package com.prismamp.todopago.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.prismamp.todopago.model.Greeting;

@Service
public class ListenerService {

	@KafkaListener(topics = "kafkaMessage", containerFactory = "kafkaListenerContainerFactory")
	public void listenMessage(String message, Acknowledgment ack) {
		System.out.println("Se recibió el mensaje: " + message);
		ack.acknowledge();
	}

	@KafkaListener(topics = "kafkaGreeting", containerFactory = "greetingKafkaListenerContainerFactory")
	public void greetingListener(Greeting greeting) {
		System.out.println("Se recibió el mensaje: " + greeting.getMsg());
		System.out.println("Se recibió el mensaje: " + greeting.getName());
	}
}
