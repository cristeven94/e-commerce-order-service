package com.ecommerce.order.domain.event;

import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent(Long orderId, Long costumerId, List<OrderItem> items, BigDecimal totalPrice,
                                OrderStatus status) {
}
