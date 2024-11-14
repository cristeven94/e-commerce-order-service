package com.ecommerce.order.domain.event;

import com.ecommerce.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderEvent {
    private Long orderId;
    private OrderStatus status;
}
