package com.statestreet.ais.interview.metrics.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

@Configuration
public class JacksonConfig {
	
	 @Bean
	 public ObjectMapper objectMapper() {
	 	ObjectMapper mapper = new ObjectMapper();
	 	Jdk8Module module = new Jdk8Module();
	    module.configureAbsentsAsNulls(true);
	 	mapper.registerModule(module);
	 	return mapper;
	 }

}
