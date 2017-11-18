package com.statestreet.ais.interview.metrics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TradeMetricsServiceFactory {

    // The default constructor; this is how we will instantiate TradeMetricsServiceFactory in our tests
    // Do not alter this constructor or add additional constructors to this class
    public TradeMetricsServiceFactory() {
        System.out.println("Instance of TradeMetricsServiceFactory created");
    }

    // Instantiate your implementation of TradeMetricsService here
    @Bean
    public TradeMetricsService createTradeMetricsService() {
        return new TradeMetricsServiceImpl();
    }

}
