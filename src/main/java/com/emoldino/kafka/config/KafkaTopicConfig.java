package com.emoldino.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {
	
	@Value(value = "${mms.topic.name}")
	private String mmsTopic;

	/** If you want to create a new topic for use with the MMS, add it below. **/
	@Bean
	public NewTopic mmsTopic() {
		return new NewTopic(mmsTopic, 4, (short) 1);
	}
	
}
