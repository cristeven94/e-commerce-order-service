package com.ecommerce.order.application.dto;

import lombok.Builder;

@Builder
public record InventoryResponse(Long productId, Long availableQuantity, String message) {
}
