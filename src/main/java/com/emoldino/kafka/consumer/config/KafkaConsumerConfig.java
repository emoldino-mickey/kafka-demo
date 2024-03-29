package com.emoldino.kafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.amazonaws.auth.AWSCredentialsProviderChain;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String kafkaUrl;

	@Bean
	public DefaultAWSCredentialsProviderChain awsCredentialsProvider() {
		return DefaultAWSCredentialsProviderChain.getInstance();
	}

	public ConsumerFactory<String, Object> consumerFactory(String groupId) {
		Map<String, Object> cfg = new HashMap<>();
		cfg.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
		cfg.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		cfg.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		cfg.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		cfg.put("security.protocol", "SASL_SSL");
		cfg.put("sasl.mechanism", "AWS_MSK_IAM");
		cfg.put("sasl.jaas.config", "software.amazon.msk.auth.iam.IAMLoginModule required;");
		cfg.put("ssl.endpoint.identification.algorithm", "https");
		cfg.put("client.id", "emoldino-kafka-demo");
		cfg.put("sasl.client.callback.handler.class", "software.amazon.msk.auth.iam.IAMClientCallbackHandler");		
		
		return new DefaultKafkaConsumerFactory<>(cfg);
	}

	public ConsumerFactory<String, Object> multiTypeConsumerFactory() {
		HashMap<String, Object> cfg = new HashMap<>();
		cfg.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
		cfg.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		cfg.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		cfg.put("security.protocol", "SASL_SSL");
		cfg.put("sasl.mechanism", "AWS_MSK_IAM");
		cfg.put("sasl.jaas.config", "software.amazon.msk.auth.iam.IAMLoginModule required;");
		cfg.put("ssl.endpoint.identification.algorithm", "https");
		cfg.put("client.id", "emoldino-kafka-demo");
		cfg.put("sasl.client.callback.handler.class", "software.amazon.msk.auth.iam.IAMClientCallbackHandler");
		
//		 DefaultAWSCredentialsProviderChain credentialsProvider = DefaultAWSCredentialsProviderChain.getInstance();
//	     cfg.put("aws.accessKeyId","AKIAZ47SBXAVM42A7FOV");
//	     cfg.put("aws.secretKey", "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");
	     
		return new DefaultKafkaConsumerFactory<>(cfg);
	}

	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(String groupId) {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(groupId));
		return factory;
	}

	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryFilterString(String groupId, String filter) {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(groupId));
		factory.setRecordFilterStrategy(record -> record.value().contains(filter)); // Ignores messages with that string.
		return factory;
	}

	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactoryFilterObject(String groupId, Object filter) {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory(groupId));
		factory.setRecordFilterStrategy(record -> record.value().equals(filter)); // Ignores messages with that object.
		return factory;
	}

	// TO-DO : Retry Processing (Retry Callback)
	// TO-DO : Multiple Type Processing 

	/** Receive messages by Group ID. Currently, the Group ID is defined only as mms. **/
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> mmsKafkaListenerContainerFactory() {
		return kafkaListenerContainerFactory("mms");
	}

}
