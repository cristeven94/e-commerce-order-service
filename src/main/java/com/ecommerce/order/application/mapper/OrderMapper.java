package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.OrderRequest;
import com.ecommerce.order.application.dto.OrderResponse;
import com.ecommerce.order.domain.model.Order;
import com.ecommerce.order.domain.model.OrderItem;
import org.mapstruct.*;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(source = "items", target = "orderItems")
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toDTO(Order order);

    @AfterMapping
    default void setOrderInItems(@MappingTarget Order order){
        List<OrderItem> items = order.getOrderItems();
        if(Objects.nonNull(items)){
            items.forEach(orderItem -> orderItem.setOrder(order));
        }
    }
}
