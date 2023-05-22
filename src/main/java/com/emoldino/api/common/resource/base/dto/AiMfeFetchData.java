package com.emoldino.api.common.resource.base.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiMfeFetchData {
	@Enumerated(EnumType.STRING)
	private AiModelType aiType;
	private int generation;	
	private AiMfeFetchFields data;
}
