package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.event.OrderCreatedEvent;
import com.ecommerce.order.domain.exception.OrderNotFoundException;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;
import com.ecommerce.order.infrastructure.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderService(OrderRepository orderRepository, KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Order createOrder(Order order) {
        BigDecimal totalPrice = calculateTotal(order.getOrderItems());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.NEW);
        Order savedOrder = orderRepository.save(order);
        log.info("Order created -> {}", order);
        kafkaTemplate.send("order-topic", buildOrderCreatedEvent(order));
        return savedOrder;
    }

    private OrderCreatedEvent buildOrderCreatedEvent(Order order){
        return new OrderCreatedEvent(order.getId(), order.getCostumerId(), order.getTotalPrice(), order.getStatus());
    }

    private BigDecimal calculateTotal(List<OrderItem> itemList){
        return itemList.stream()
                .map(orderItem -> orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Order getOrderById(Long orderId){
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }
}
