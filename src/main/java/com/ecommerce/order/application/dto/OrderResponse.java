package com.ecommerce.order.application.dto;

import com.ecommerce.order.domain.model.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record OrderResponse(Long id, Long costumerId, BigDecimal totalPrice, OrderStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
