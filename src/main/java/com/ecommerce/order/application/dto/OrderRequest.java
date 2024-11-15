package com.ecommerce.order.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(Long costumerId, List<OrderItemRequest> items) {
}
