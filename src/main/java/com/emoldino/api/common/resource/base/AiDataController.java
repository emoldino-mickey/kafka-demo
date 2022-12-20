package com.emoldino.api.common.resource.base;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.emoldino.api.common.resource.base.dto.AiDataIn;
import com.emoldino.framework.dto.SuccessOut;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(protocols = "http, https", tags = "Kafka Test")
@RequestMapping("/api/common/test")
public interface AiDataController {
	
	@ApiOperation("Test")
	@GetMapping
	SuccessOut test();
		
	@ApiOperation("Data Send Test")
	@PostMapping("/send")
	SuccessOut send(@RequestBody AiDataIn input);
}
