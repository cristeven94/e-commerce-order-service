package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.OrderItemRequest;
import com.ecommerce.order.application.dto.OrderItemResponse;
import com.ecommerce.order.domain.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemMapper {

    OrderItemRequest toEntity(OrderItem item);

    OrderItem toDto(OrderItemResponse itemResponse);
}
