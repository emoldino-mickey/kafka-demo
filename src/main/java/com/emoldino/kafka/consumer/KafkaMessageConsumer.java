package com.emoldino.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;


import com.emoldino.api.common.resource.base.dto.AiData;
import com.emoldino.framework.util.ValueUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageConsumer {

	@KafkaListener(topics = "#{'${mms.topic.name}'}", groupId = "mms", containerFactory = "mmsKafkaListenerContainerFactory")	
	public void receiveFromAI(@Payload ConsumerRecord<?, ?> consumerRecord, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		if (!ObjectUtils.isEmpty(consumerRecord)) {
			log.info("Receive message=[" + consumerRecord.toString() + "]");
			AiData data = ValueUtils.fromJsonStr((String) consumerRecord.value(), AiData.class);
			if (!ObjectUtils.isEmpty(data))
				log.info("id = " + data.getId() + ", data = " + data.getResult());
		}
	}
		
	// Basic Kafka Listener
    // @KafkaListener(topics = "#{'${mms.topic.name}'}")
	public void receiveFromAI(ConsumerRecord<?, ?> consumerRecord) {
		if(!ObjectUtils.isEmpty(consumerRecord)) { 
			log.info("Receive message=[" + consumerRecord.toString() + "]");			
			AiData data = ValueUtils.fromJsonStr((String) consumerRecord.value(), AiData.class);	
			if(!ObjectUtils.isEmpty(data))
				log.info("id = " + data.getId() + ", data = " + data.getResult());
		}        
	}
}


