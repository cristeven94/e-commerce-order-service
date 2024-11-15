package com.ecommerce.order.application.service;

import com.ecommerce.order.domain.event.OrderCreatedEvent;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import com.ecommerce.order.domain.model.OrderStatus;
import com.ecommerce.order.infrastructure.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock
    KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @InjectMocks
    OrderService service;

    Order order;


    @BeforeEach
    void setUp(){

        OrderItem item = OrderItem.builder()
                .id(1L)
                .quantity(2)
                .price(BigDecimal.valueOf(200))
                .order(order)
                .build();

        OrderItem item1 = OrderItem.builder()
                .id(2L)
                .quantity(1)
                .price(BigDecimal.valueOf(100))
                .order(order)
                .build();

        List<OrderItem> itemList = List.of(item, item1);

        order = Order.builder()
                .id(1L)
                .orderItems(itemList)
                .costumerId(1L)
                .build();
    }

    @Test
    void createOrderSuccessful() {

        when(orderRepository.save(any(Order.class))).thenReturn(order);
        doNothing().when(kafkaTemplate.send(anyString(),any(OrderCreatedEvent.class)));

        Order savedOrder = service.createOrder(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getTotalPrice()).isEqualByComparingTo("500");
        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(savedOrder.getOrderItems())
                .extracting(OrderItem::getPrice)
                .containsExactly(BigDecimal.valueOf(200), BigDecimal.valueOf(100));
    }

}