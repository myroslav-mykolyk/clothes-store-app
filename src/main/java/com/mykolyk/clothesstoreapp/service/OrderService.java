package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;

public interface OrderService {
    OrderDto getOrder(Long id);

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    OrderDto addOrderItemToOrder(Long id, OrderItemDto orderItemDto);

    OrderDto payFoOrder(Long id);

    void deleteOrder(Long id);

    void deleteUnpaidOrders();
}
