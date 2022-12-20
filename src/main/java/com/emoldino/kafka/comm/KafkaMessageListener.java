package com.emoldino.kafka.comm;

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

@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

	@KafkaListener(topics = "#{'${mms.topic.name}'}", groupId = "mms", containerFactory = "mmsKafkaListenerContainerFactory")	
	public void receiveFromAI(@Payload ConsumerRecord<?, ?> consumerRecord, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
		if (!ObjectUtils.isEmpty(consumerRecord)) {
			System.out.println("Receive message=[" + consumerRecord.toString() + "]");
			AiData data = ValueUtils.fromJsonStr((String) consumerRecord.value(), AiData.class);
			if (!ObjectUtils.isEmpty(data))
				System.out.println("id = " + data.getId() + ", data = " + data.getResult());
		}
	}
		
//@KafkaListener(topics = "#{'${mms.topic.name}'}")
//	public void receiveFromAI(ConsumerRecord<?, ?> consumerRecord) {
//		if(!ObjectUtils.isEmpty(consumerRecord)) { 
//			System.out.println("Receive message=[" + consumerRecord.toString() + "]");			
//			AiData data = ValueUtils.fromJsonStr((String) consumerRecord.value(), AiData.class);	
//			if(!ObjectUtils.isEmpty(data))
//			System.out.println("id = " + data.getId() + ", data = " + data.getResult());
//		}        
//	}
}
