package com.ecommerce.order.application.mapper;

import com.ecommerce.order.application.dto.InventoryResponse;
import com.ecommerce.order.domain.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InventoryMapper {
    Inventory toEntity(InventoryResponse inventoryResponse);
}
