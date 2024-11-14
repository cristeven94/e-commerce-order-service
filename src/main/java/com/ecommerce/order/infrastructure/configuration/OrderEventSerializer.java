package com.ecommerce.order.infrastructure.configuration;

import com.ecommerce.order.domain.event.OrderCreatedEvent;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;

public class OrderEventSerializer implements Serializer<OrderCreatedEvent> {

    @Override
    public void serialize(OrderCreatedEvent object, OutputStream outputStream) throws IOException {

    }
}
