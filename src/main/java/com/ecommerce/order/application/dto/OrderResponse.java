package com.ecommerce.order.application.dto;

import com.ecommerce.order.domain.model.OrderStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderResponse(Long id, Long costumerId, BigDecimal totalPrice, OrderStatus status,
                            List<OrderItemResponse> items, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
