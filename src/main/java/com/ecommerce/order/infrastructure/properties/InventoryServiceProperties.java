package com.ecommerce.order.infrastructure.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Optional;

@ConfigurationProperties(prefix = "inventory-service")
@Configuration
@Getter
public class InventoryServiceProperties {
    private String rootUrl;
    private Map<String, String> endpoints;

    public Optional<String> getEndpointUri(String endpointName){
        return Optional.ofNullable(endpoints.get(endpointName));
    }
}
