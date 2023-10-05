package com.mykolyk.clothesstoreapp.service.mapping;

import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.model.Order;

public interface OrderMappingService {
    OrderDto mapOrderToOrderDto(Order order);

    Order mapOrderDtoToOrder(OrderDto orderDto);

    Order populateOrderWithPresentOrderDtoFields(Order order, OrderDto orderDto);
}
