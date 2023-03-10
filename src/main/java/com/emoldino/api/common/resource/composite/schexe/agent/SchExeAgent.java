package com.emoldino.api.common.resource.composite.schexe.agent;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.emoldino.api.common.resource.base.dto.AiData;
import com.emoldino.api.common.resource.base.dto.AiDataIn;
import com.emoldino.framework.util.ValueUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//@Profile("!developer")
@EnableScheduling
@Component
public class SchExeAgent {
	
	@Value(value = "${mms.topic.name}")
	private String mmsTopic;
//		
//	@KafkaListener(topics = "#{'${mms.topic.name}'}")
//	public void receiveFromAI(ConsumerRecord<?, ?> consumerRecord) {
//		if(!ObjectUtils.isEmpty(consumerRecord)) { 
//			System.out.println("Receive message=[" + consumerRecord.toString() + "]");			
//			AiData data = ValueUtils.fromJsonStr((String) consumerRecord.value(), AiData.class);	
//			if(!ObjectUtils.isEmpty(data))
//			System.out.println("id = " + data.getId() + ", data = " + data.getResult());
//		}        
//	}
	
}
