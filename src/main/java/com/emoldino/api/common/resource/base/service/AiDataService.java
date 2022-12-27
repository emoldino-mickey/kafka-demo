package com.emoldino.api.common.resource.base.service;

import org.springframework.stereotype.Service;

import com.emoldino.api.common.resource.base.dto.AiDataIn;
import com.emoldino.kafka.producer.KafkaMessageProducer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AiDataService {
	
    private final KafkaMessageProducer kafkaMessageProducer;

	public void send(AiDataIn input) {
		kafkaMessageProducer.sendMessage(input);	
	}

}
