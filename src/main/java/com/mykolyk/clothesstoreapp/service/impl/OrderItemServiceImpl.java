package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.exception.OrderItemAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.OrderItemNotFoundException;
import com.mykolyk.clothesstoreapp.exception.UserAlreadyExistsException;
import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.OrderItemRepository;
import com.mykolyk.clothesstoreapp.service.OrderItemService;
import com.mykolyk.clothesstoreapp.service.mapping.OrderItemMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private  final OrderItemRepository orderItemRepository;
    private final OrderItemMappingService orderItemMappingService;
    @Override
    public OrderItemDto getOrderItem(Long id) {
        log.info("Getting order item by id: {}", id);
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        log.info("Founded order item with id: {}", id);
        return orderItemMappingService.mapOrderItemToOrderItemDto(orderItem);
    }

    @Override
    @Transactional
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        log.info("Creating order item with id: {}", orderItemDto.getId());
        if (orderItemRepository.existsById(orderItemDto.getId())) {
            throw new OrderItemAlreadyExistException();
        }
        OrderItem orderItem = orderItemMappingService.mapOrderItemDtoToOrderItem(orderItemDto);
        orderItem = orderItemRepository.save(orderItem);
        log.info("Created order item with id: {}", orderItem.getId());
        return orderItemMappingService.mapOrderItemToOrderItemDto(orderItem);
    }

    @Override
    @Transactional
    public OrderItemDto updateOrderItem(Long id, OrderItemDto orderItemDto) {
        log.info("Updating order item with id: {}", id);
        OrderItem persistedOrderItem = orderItemRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        persistedOrderItem = orderItemMappingService.populateOrderItemWithPresentOrderItemDtoFields(persistedOrderItem, orderItemDto);
        OrderItem storedOrderItem = orderItemRepository.save(persistedOrderItem);
        log.info("Updated order item with id: {}", storedOrderItem.getId());
        return orderItemMappingService.mapOrderItemToOrderItemDto(persistedOrderItem);
    }

    @Override
    @Transactional
    public void deleteOrderItem(Long id) {
        log.info("Deleting order item with id: {}", id);
        OrderItem orderItem = orderItemRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        orderItemRepository.delete(orderItem);
        log.info("Deleted order item with email: {}", id);
    }
}
