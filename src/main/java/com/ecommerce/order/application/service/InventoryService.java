package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.model.Inventory;
import com.ecommerce.order.infrastructure.properties.InventoryServiceProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Log4j2
public class InventoryService {

    public static final String CHECK_INVENTORY = "check-inventory";

    private final RestTemplate restTemplate;
    private final InventoryServiceProperties properties;

    public InventoryService(RestTemplate restTemplate, InventoryServiceProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventoryCheck")
    public Inventory checkInventory(Long productId) {
        String url = getInventoryCheckUrl(productId);
        return restTemplate.getForObject(url, Inventory.class);
    }

    private boolean fallbackInventoryCheck(Long productId, Throwable throwable) {
        log.error("Fallback executed due to: {}", throwable.getMessage());
        return false;
    }

    private String getInventoryCheckUrl(Long productId) {
        return UriComponentsBuilder
                .fromHttpUrl(properties.getRootUrl())
                .path(properties.getEndpoints().get(CHECK_INVENTORY))
                .buildAndExpand(productId)
                .toUriString();
    }
}
