package com.mykolyk.clothesstoreapp.service.mapping;

import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.model.OrderItem;

public interface OrderItemMappingService {
    OrderItemDto mapOrderItemToOrderItemDto(OrderItem orderItem);

    OrderItem mapOrderItemDtoToOrderItem(OrderItemDto orderItemDto);

    OrderItem populateOrderItemWithPresentOrderItemDtoFields(OrderItem orderItem, OrderItemDto orderItemDto);
}
