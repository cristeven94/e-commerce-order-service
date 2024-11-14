package com.ecommerce.order.application.dto;

public record OrderResponse(Long id, String productName, int quantity, double totalAmount) {
}
