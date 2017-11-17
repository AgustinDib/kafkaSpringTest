package com.prismamp.todopago.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.prismamp.todopago.model.Greeting;
import com.prismamp.todopago.service.ListenerService;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

	private String bootstrapAddress = "localhost:9092";

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "holaMundo");

		return props;
	}

	@Bean
	public Map<String, Object> geetingConsumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "holaMundo");

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public ConsumerFactory<String, Greeting> greetingConsumerFactory() {
		return new DefaultKafkaConsumerFactory<>(geetingConsumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(Greeting.class));
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Greeting> greetingKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Greeting> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(greetingConsumerFactory());

		return factory;
	}

	@Bean
	public ListenerService listenerService() {
		return new ListenerService();
	}
}
