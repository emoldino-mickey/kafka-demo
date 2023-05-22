package com.emoldino.api.common.resource.base.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.emoldino.api.common.resource.base.dto.AiDataIn;
import com.emoldino.api.common.resource.base.dto.AiMfeFetchData;
import com.emoldino.api.common.resource.base.dto.AiMfeFetchFields;
import com.emoldino.api.common.resource.base.dto.AiModelType;
import com.emoldino.api.common.resource.base.dto.KafkaMessage;
import com.emoldino.framework.util.ValueUtils;
import com.emoldino.kafka.producer.KafkaMessageProducer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AiDataService {
	
    private final KafkaMessageProducer kafkaMessageProducer;
        
    @Value("#{'${mms.topic.name}'.trim()}")
    private String mmsTopic;
    
    @Value("#{'${ai.fetch.topic.name}'.trim()}")
    private String aiFetchTopic;

	public void send(AiDataIn input) {
		kafkaMessageProducer.sendMessage(input);	
	}
	
	public void send2() {
		List<String> tff = new ArrayList<String>();
		List<String> hour = new ArrayList<String>();
		List<Integer> shotCount = new ArrayList<Integer>();
		List<Integer> tav = new ArrayList<Integer>();
		List<Integer> cycleTime = new ArrayList<Integer>();

		tff.add("20230504044207");
		tff.add("20230504042732");
		tff.add("20230504041717");
		tff.add("20230504040819");
		tff.add("20230504034206");
		tff.add("20230504032730");
		tff.add("20230504030817");
		tff.add("20230504031713");
		
		hour.add("2023050404");
		hour.add("2023050404");
		hour.add("2023050404");
		hour.add("2023050404");
		hour.add("2023050403");
		hour.add("2023050403");
		hour.add("2023050403");
		hour.add("2023050403");
		
		shotCount.add(0);
		shotCount.add(97);
		shotCount.add(0);
		shotCount.add(103);
		shotCount.add(0);
		shotCount.add(97);
		shotCount.add(104);
		shotCount.add(0);
		
		tav.add(253);
		tav.add(294);
		tav.add(354);
		tav.add(512);
		tav.add(245);
		tav.add(323);
		tav.add(564);
		tav.add(349);
		
		cycleTime.add(371);
		cycleTime.add(0);
		cycleTime.add(347);
		cycleTime.add(0);
		cycleTime.add(371);
		cycleTime.add(374);
		cycleTime.add(232);
		cycleTime.add(988);
		
		
		AiMfeFetchFields fetchFields = AiMfeFetchFields.builder() //
				.moldId(20230325L) //
				.tff(tff) //
				.hour(hour) //
				.shotCount(shotCount) //
				.tav(tav) //
				.cycleTime(cycleTime) //
				.build();
		
		
		AiMfeFetchData fetchData = AiMfeFetchData.builder() //
				.aiType(AiModelType.AI_MOLD_FEATURE_EXT).generation(2).data(fetchFields).build();
		
		Map<String, Object> content = ValueUtils.toRequiredType(fetchData, Map.class);
		KafkaMessage msg = KafkaMessage.builder() //
				.message("mmsai_mfe_fetch") //
				.topic(aiFetchTopic) //
				.returnTopic(mmsTopic) //
				.content(content) //
				.build();
		
		kafkaMessageProducer.sendMessage(msg);
	}

}
