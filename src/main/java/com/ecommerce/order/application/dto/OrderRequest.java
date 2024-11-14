package com.ecommerce.order.application.dto;

public record OrderRequest(String productName, int quantity, double price) {
}
