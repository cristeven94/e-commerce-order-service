package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.OrderRequest;
import com.ecommerce.order.application.dto.OrderResponse;
import com.ecommerce.order.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderRequest orderRequest);
    OrderResponse toDTO(Order order);
}
