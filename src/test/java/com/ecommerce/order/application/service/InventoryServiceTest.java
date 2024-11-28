package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.model.Inventory;
import com.ecommerce.order.infrastructure.properties.InventoryServiceProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @InjectMocks
    InventoryService inventoryService;

    @Mock
    WebClient restTemplate;

    @Mock
    InventoryServiceProperties properties;

    Inventory inventory;

    @BeforeEach
    void beforeEach() {
        inventory = Inventory.builder().productId(1L)
                .availableQuantity(1)
                .build();
    }

    @Test
    void shouldReturnTrueWhenInventoryServiceIsUp() {
        Long productId = 1L;
        when(restTemplate.getForObject(anyString(),any())).thenReturn(inventory);

        boolean result = inventoryService.checkInventory(productId);

        assertThat(result).isTrue();
    }

    @Test
    void shouldFallbackWhenInventoryServiceFalls(){
        Long productId = 1L;
        when(restTemplate.getForObject(anyString(), any())).thenThrow(new RuntimeException("Inventory service down"));

        boolean result = inventoryService.checkInventory(productId);

        assertThat(result).isFalse();
    }

}