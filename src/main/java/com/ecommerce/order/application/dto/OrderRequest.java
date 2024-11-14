package com.ecommerce.order.application.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record OrderRequest(Long costumerId, BigDecimal totalPrice, List<OrderItemRequest> items) {
}
