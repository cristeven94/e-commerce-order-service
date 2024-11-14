package com.ecommerce.order.application.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderItemRequest(Long productId, int quantity, BigDecimal price) {
}
