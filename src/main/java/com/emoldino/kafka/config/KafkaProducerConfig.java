package com.emoldino.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.emoldino.api.common.resource.base.dto.AiData;

@Configuration
public class KafkaProducerConfig {

	@Value(value = "${spring.kafka.bootstrap-servers}")
	private String kafkaUrl;
	
	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;

	@Bean
	public ProducerFactory<String, Object> producerFactory() {
		Map<String, Object> cfg = new HashMap<>();
		cfg.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl); // Kafka URL
		cfg.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);		
		cfg.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// cfg.put(ProducerConfig.ACKS_CONFIG, "all"); // Message 수신 완료 신호 설정
		return new DefaultKafkaProducerFactory<>(cfg);
	}
	
	@Bean 
	public KafkaTemplate<String, Object> kafkaTempleate(){
		return new KafkaTemplate<String, Object>(producerFactory());
	}
	
	/** If you want to create a new templeate for use with the MMS, add it below. **/
	
	/* --- Single Type SAMPLE ---
	@Bean
	public ProducerFactory<String, AiData> aiDataProducerFactory() {
		Map<String, Object> cfg = new HashMap<>();
		cfg.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl); // Kafka URL
		cfg.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);		
		cfg.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		// cfg.put(ProducerConfig.ACKS_CONFIG, "all"); // Message 수신 완료 신호 설정
		return new DefaultKafkaProducerFactory<>(cfg);
	}
	
	@Bean
	public KafkaTemplate<String, AiData> aiDataTempleate() {
		return new KafkaTemplate<String, AiData>(aiDataProducerFactory());
	}
	
	*/
	
	/* --- Multi Type SAMPLE ---
    @Bean
    public ProducerFactory<String, Object> multiTypeProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaUrl);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.TYPE_MAPPINGS, "aiData: com.emoldino.api.common.resource.base.dto.AiData, aiDataIn: com.emoldino.api.common.resource.base.dto.AiDataIn");
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> multiTypeKafkaTemplate() {
        return new KafkaTemplate<>(multiTypeProducerFactory());
    }
    */

}
