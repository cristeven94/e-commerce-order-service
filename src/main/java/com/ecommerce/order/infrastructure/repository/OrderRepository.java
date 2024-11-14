package com.ecommerce.order.infrastructure.repository;

import com.ecommerce.order.domain.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
