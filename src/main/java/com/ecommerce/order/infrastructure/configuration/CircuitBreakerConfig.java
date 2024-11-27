package com.ecommerce.order.infrastructure.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreaker inventoryServiceCircuitBreaker(CircuitBreakerRegistry registry){
        return registry.circuitBreaker("inventoryService");
    }
}
