package com.ecommerce.order.infrastructure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties(prefix = "inventory-service")
@Configuration
@Getter
@Setter
public class InventoryServiceProperties {
    private String rootUrl;
    private Map<String, String> endpoints;
}
