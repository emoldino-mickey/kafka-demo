package com.emoldino.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;

@Configuration
public class KafkaConfig {
	
	@Value(value = "${spring.kafka.bootstrap-servers}")
    private String kafkaAddress;
		
	@Value(value = "${mms.topic.name}")
	private String mmsTopic;

	
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> cfgs = new HashMap<>();
        cfgs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        return new KafkaAdmin(cfgs);
    }
	
	/** If you want to create a new topic for use with the MMS, add it below. **/
	@Bean
	public NewTopic mmsTopic() {
		return TopicBuilder.name(mmsTopic) //
				.partitions(4) //
				.replicas(1) //				
				.build();
	}	
	
	
}
