package com.ecommerce.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Inventory implements Serializable {
    private Long productId;
    private int desiredQuantity;
    private boolean available;
    private int availableQuantity;
}
