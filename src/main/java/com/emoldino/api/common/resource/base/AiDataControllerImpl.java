package com.emoldino.api.common.resource.base;

import org.springframework.web.bind.annotation.RestController;

import com.emoldino.api.common.resource.base.dto.AiDataIn;
import com.emoldino.api.common.resource.base.service.AiDataService;
import com.emoldino.framework.dto.SuccessOut;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AiDataControllerImpl implements AiDataController {

	private final AiDataService service;
	
	@Override
	public SuccessOut test() {
		return null;
	}

	@Override
	public SuccessOut send(AiDataIn input) {
//		service.send(input);
		service.send2();
		return SuccessOut.getDefault();
	}

}
