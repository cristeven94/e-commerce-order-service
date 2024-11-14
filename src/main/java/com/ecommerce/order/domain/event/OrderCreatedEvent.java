package com.ecommerce.order.domain.event;

import com.ecommerce.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class OrderCreatedEvent {
    private Long orderId;
    private Long costumerId;
    private BigDecimal totalPrice;
    private OrderStatus status;
}
