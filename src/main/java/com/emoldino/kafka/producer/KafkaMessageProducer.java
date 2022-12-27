package com.emoldino.kafka.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaMessageProducer {

	@Value(value = "${ai.fetch.topic.name}")
	private String aiFetchTopic;
	
	private final KafkaTemplate<String, Object> kafkaTemplate;

	public void sendMessage(Object message) {

		ListenableFuture<SendResult<String, Object>> fu = kafkaTemplate.send(aiFetchTopic, message);
		fu.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.info("Sent message =" + message.toString());
				log.info("offset = " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message= " + message.toString());
				log.error("Exception = " + ex.getMessage());
			}
		});
	}

	public void sendMessage(String topic, Object message) {

		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> result) {
				log.info("Sent message =" + message.toString());
				log.info("offset = " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				log.info("Unable to send message= " + message.toString());
				log.error("Exception = " + ex.getMessage());
			}
		});
	}

}
