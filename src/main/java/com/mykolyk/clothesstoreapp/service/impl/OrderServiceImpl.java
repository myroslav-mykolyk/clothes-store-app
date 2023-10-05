package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.exception.OrderAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.OrderItemNotFoundException;
import com.mykolyk.clothesstoreapp.exception.OrderNotFoundException;
import com.mykolyk.clothesstoreapp.model.Order;
import com.mykolyk.clothesstoreapp.repository.OrderRepository;
import com.mykolyk.clothesstoreapp.service.OrderService;
import com.mykolyk.clothesstoreapp.service.mapping.OrderMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private final OrderMappingService orderMappingService;

    @Override
    public OrderDto getOrder(Long id) {
        log.info("Getting order by id: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        log.info("Founded order with id: {}", id);
        return orderMappingService.mapOrderToOrderDto(order);
    }

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Creating order with id: {}", orderDto.getId());
        if (orderRepository.existsById(orderDto.getId())) {
            throw new OrderAlreadyExistException();
        }
        Order order = orderMappingService.mapOrderDtoToOrder(orderDto);
        order = orderRepository.save(order);
        log.info("Created order with id: {}", order.getId());
        return orderMappingService.mapOrderToOrderDto(order);
    }

    @Override
    @Transactional
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        log.info("Updating order with id: {}", id);
        Order persistedOrder = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        persistedOrder = orderMappingService.populateOrderWithPresentOrderDtoFields(persistedOrder, orderDto);
        Order storedOrder = orderRepository.save(persistedOrder);
        log.info("Updated order with id: {}", storedOrder.getId());
        return orderMappingService.mapOrderToOrderDto(persistedOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        log.info("Deleting order with id: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        orderRepository.delete(order);
        log.info("Deleted order with id: {}", id);
    }
}
