package com.mykolyk.clothesstoreapp.service.mapping.impl;

import com.mykolyk.clothesstoreapp.dto.OrderDto;

import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.Order;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.OrderItemRepository;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.mapping.OrderMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
                .isPaid(order.getIsPaid())
                .build();
    }


    //TODO: Refactor getting of orderItems to make less affect on DB!
    @Override
    public Order mapOrderDtoToOrder(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .user(userRepository.findByEmail(orderDto.getUserEmail()).orElseThrow(UserNotFoundException::new))
                .orderItems(getOrderItems(orderDto.getOrderItemsIds()))
                .creationTime(orderDto.getCreationTime())
                .modificationTime(orderDto.getModificationTime())
                .isPaid(orderDto.getIsPaid())
                .build();
    }

    @Override
    public Order populateOrderWithPresentOrderDtoFields(Order order, OrderDto orderDto) {
        User user = userRepository.findByEmail(orderDto.getUserEmail()).orElseThrow(UserNotFoundException::new);
        if(Objects.nonNull(user)) {
            order.setUser(user);
        }
        LocalDateTime creationTime = orderDto.getCreationTime();
        if(Objects.nonNull(creationTime)) {
            order.setCreationTime(creationTime);
        }
        LocalDateTime modificationTime = orderDto.getModificationTime();
        if(Objects.nonNull(modificationTime)) {
            order.setCreationTime(modificationTime);
        }
        Boolean isPaid = orderDto.getIsPaid();
        if(Objects.nonNull(isPaid)) {
            order.setIsPaid(isPaid);
        }
        return order;
    }

    private List<Long> getOrderItemsIds(List<OrderItem> orderItems) {
        return orderItems.stream().map(OrderItem::getId).collect(Collectors.toList());
    }

    private List<OrderItem> getOrderItems(List<Long> ordersItemIds) {
        return ordersItemIds.stream().map(orderItemRepository::getReferenceById).collect(Collectors.toList());
    }
}
