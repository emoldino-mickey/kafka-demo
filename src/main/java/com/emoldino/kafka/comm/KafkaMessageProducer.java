package com.emoldino.kafka.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class KafkaMessageProducer {

	@Value(value = "${ai.fetch.topic.name}")
	private String aiFetchTopic;
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
    public void sendMessage(Object message) {

        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(aiFetchTopic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + message.toString() + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=[" + message.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
	
}
