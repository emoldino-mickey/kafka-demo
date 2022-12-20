package com.emoldino.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.scheduling.annotation.EnableAsync;

import com.emoldino.kafka.comm.KafkaMessageProducer;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = { "com.emoldino" })
@EnableAsync
public class KafkaDemoApplication {
		
	public static void main(String[] args) {			   	    	   	    	    	    
		SpringApplication application = new SpringApplication(KafkaDemoApplication.class);
	    application.run(args);		
	}
}
