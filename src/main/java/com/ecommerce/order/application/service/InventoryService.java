package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.exception.EndpointInPropertiesNotFound;
import com.ecommerce.order.domain.model.Inventory;
import com.ecommerce.order.infrastructure.properties.InventoryServiceProperties;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Service
@Log4j2
public class InventoryService {

    public static final String CHECK_INVENTORY_ENDPOINT = "check-inventory";

    private final WebClient inventoryClient;
    private final InventoryServiceProperties properties;

    public InventoryService(WebClient inventoryClient, InventoryServiceProperties properties) {
        this.inventoryClient = inventoryClient;
        this.properties = properties;
    }

    @CircuitBreaker(name = "inventoryServiceCircuitBreaker", fallbackMethod = "fallbackInventoryCheck")
    public boolean checkInventory(Long productId) {
        Inventory item = inventoryClient.get()
                .uri(getEndpointUriFromProperties(CHECK_INVENTORY_ENDPOINT), productId)
                .retrieve()
                .bodyToMono(Inventory.class)
                .block();
        return Objects.nonNull(item) && item.isAvailable();
    }

    private boolean fallbackInventoryCheck(Long productId, Throwable throwable) {
        log.error("Fallback executed due to: {}", throwable.getMessage());
        return false;
    }

    private String getEndpointUriFromProperties(String endpointName) {
        return properties.getEndpointUri(endpointName)
                .orElseThrow(() -> new EndpointInPropertiesNotFound(endpointName));
    }


}
