package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.OrderItemDto;

public interface OrderItemService {
    OrderItemDto getOrderItem(Long id);

    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto);

    void deleteOrderItem(Long id);
}
