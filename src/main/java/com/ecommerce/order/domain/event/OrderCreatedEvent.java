package com.ecommerce.order.domain.event;

import com.ecommerce.order.domain.model.OrderStatus;

import java.math.BigDecimal;

public record OrderCreatedEvent(Long orderId, Long costumerId, BigDecimal totalPrice, OrderStatus status) {
}
