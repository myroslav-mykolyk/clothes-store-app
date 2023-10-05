package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.OrderDto;

public interface OrderService {
    OrderDto getOrder(Long id);

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(Long id, OrderDto orderDto);

    void deleteOrder(Long id);
}
