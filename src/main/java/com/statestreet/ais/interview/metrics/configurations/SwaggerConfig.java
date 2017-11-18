package com.statestreet.ais.interview.metrics.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        		.select().apis(RequestHandlerSelectors.any())
                .paths(apiPaths())
                .build()
                .apiInfo(buildApiInfo());    
    }
    
    @SuppressWarnings("deprecation")
	private ApiInfo buildApiInfo() {
    	ApiInfo apiInfo = new ApiInfo("Trade Metrics Services", "Use this page to invoke the Trade Metrics services ", "1.0", "", "", "", "");
    	return apiInfo;
    }
    
    private Predicate<String> apiPaths() {
    	return Predicates.or (PathSelectors.regex("/trademetrics"), PathSelectors.regex("/trademetrics/summary"));
    }

}
