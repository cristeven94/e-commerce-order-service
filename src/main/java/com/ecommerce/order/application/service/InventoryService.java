package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.model.Inventory;
import com.ecommerce.order.infrastructure.properties.InventoryServiceProperties;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class InventoryService {

    public static final String CHECK_INVENTORY = "check-inventory";

    private final RestTemplate restTemplate;
    private final InventoryServiceProperties properties;

    public InventoryService(RestTemplate restTemplate, InventoryServiceProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }


    public Inventory checkInventory(Long productId) {
        String url = getInventoryCheckUrl(productId);
        return restTemplate.getForObject(url, Inventory.class);
    }

    private String getInventoryCheckUrl(Long productId) {
        return UriComponentsBuilder
                .fromHttpUrl(properties.getRootUrl())
                .path(properties.getEndpoints().get(CHECK_INVENTORY))
                .buildAndExpand(productId)
                .toUriString();
    }
}
