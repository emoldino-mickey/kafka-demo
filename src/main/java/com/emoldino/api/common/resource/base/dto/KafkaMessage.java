package com.emoldino.api.common.resource.base.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KafkaMessage {
	String message;
	String topic;
	String returnTopic;
	private Map<String, Object> content;
}
