package com.mykolyk.clothesstoreapp.service.mapping.impl;

import com.mykolyk.clothesstoreapp.dto.OrderDto;

import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.Order;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.repository.GoodRepository;
import com.mykolyk.clothesstoreapp.repository.OrderItemRepository;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.mapping.OrderMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMappingServiceImpl implements OrderMappingService {
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto mapOrderToOrderDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .userEmail(order.getUser().getEmail())
                .orderItemsIds(getOrderItemsIds(order.getOrderItems()))
                .creationTime(order.getCreationTime())
                .modificationTime(order.getModificationTime())
                .isPayed(order.isPayed())
                .build();
    }


    //TODO: Refactor orderItems mapping!
    @Override
    public Order mapOrderDtoToOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .user(userRepository.findByEmail(orderDto.getUserEmail()).orElseThrow(UserNotFoundException::new))
                .orderItems(orderItemRepository.findAll())
                .creationTime(orderDto.getCreationTime())
                .modificationTime(orderDto.getModificationTime())
                .isPayed(orderDto.isPayed())
                .build();
    }

    //TODO: Implement method
    @Override
    public Order populateOrderWithPresentOrderDtoFields(Order order, OrderDto orderDto) {
        return null;
    }

    private List<Long> getOrderItemsIds(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItem::getId).collect(Collectors.toList());
    }
}
