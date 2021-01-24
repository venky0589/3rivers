package com.rivers.account.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.MessageHeaders;

import com.rivers.account.model.AccountPayload;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Bean
	public ConsumerFactory<String, AccountPayload> accountConsumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_account");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(AccountPayload.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AccountPayload> accountKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, AccountPayload> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(accountConsumerFactory());
		factory.setErrorHandler(new SeekToCurrentErrorHandler());
		return factory;
	}
	
	@Bean
	public ConsumerAwareListenerErrorHandler listen3ErrorHandler() {
	    return (m, e, c) -> {
//	        this.listen3Exception = e;
	        MessageHeaders headers = m.getHeaders();
	        c.seek(new org.apache.kafka.common.TopicPartition(
	                headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
	                headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class)),
	                headers.get(KafkaHeaders.OFFSET, Long.class));
	        return null;
	    };
	}

}