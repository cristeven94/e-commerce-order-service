package com.ecommerce.order.infrastructure.configuration;

import com.ecommerce.order.infrastructure.properties.InventoryServiceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private final InventoryServiceProperties properties;

    public WebClientConfig(InventoryServiceProperties properties) {
        this.properties = properties;
    }

    @Bean
    public WebClient inventoryClient(WebClient.Builder builder){
        return builder.baseUrl(properties.getRootUrl()).build();
    }
}
