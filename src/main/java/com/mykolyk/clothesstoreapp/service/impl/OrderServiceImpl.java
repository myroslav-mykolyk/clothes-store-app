package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.exception.OrderAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.OrderItemNotFoundException;
import com.mykolyk.clothesstoreapp.exception.OrderNotFoundException;
import com.mykolyk.clothesstoreapp.model.Order;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.repository.OrderRepository;
import com.mykolyk.clothesstoreapp.service.OrderItemService;
import com.mykolyk.clothesstoreapp.service.OrderService;
import com.mykolyk.clothesstoreapp.service.mapping.OrderItemMappingService;
import com.mykolyk.clothesstoreapp.service.mapping.OrderMappingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private  final OrderRepository orderRepository;
    private final OrderMappingService orderMappingService;
    private final OrderItemService orderItemService;
    private final OrderItemMappingService orderItemMappingService;

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
        order.setCreationTime(LocalDateTime.now());
        order.setModificationTime(LocalDateTime.now());
        order.setIsPaid(false);
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
        persistedOrder.setModificationTime(LocalDateTime.now());
        Order storedOrder = orderRepository.save(persistedOrder);
        log.info("Updated order with id: {}", storedOrder.getId());
        return orderMappingService.mapOrderToOrderDto(persistedOrder);
    }

    @Override
    @Transactional
    public OrderDto addOrderItemToOrder(Long id, OrderItemDto orderItemDto) {
        log.info("Adding orderItem with id: {} to order with id: {}", orderItemDto.getId(), id);
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        OrderItem orderItem = orderItemMappingService.mapOrderItemDtoToOrderItem(orderItemDto);
        order.getOrderItems().add(orderItem);
        order.setModificationTime(LocalDateTime.now());
        order = orderRepository.save(order);
        log.info("Added orderItem with id: {} to order with id: {}", orderItemDto.getId(), id);
        return orderMappingService.mapOrderToOrderDto(order);
    }

    @Override
    @Transactional
    public OrderDto payFoOrder(Long id) {
        log.info("Paying for order with id: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        if(order.getIsPaid()) {
            //TODO: Add related exception!
            throw new RuntimeException();
        }
        order.setIsPaid(true);
        order = orderRepository.save(order);
        log.info("Payed for order with id: {}", id);
        return orderMappingService.mapOrderToOrderDto(order);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        log.info("Deleting order with id: {}", id);
        Order order = orderRepository.findById(id).orElseThrow(OrderItemNotFoundException::new);
        order.getOrderItems().forEach(orderItem -> orderItemService.deleteOrderItem(orderItem.getId()));
        orderRepository.delete(order);
        log.info("Deleted order with id: {}", id);
    }

    @Override
    @Transactional
    @Scheduled(fixedRate = 600000) // 10 minutes (10 * 60 * 1000 milliseconds)
    public void deleteUnpaidOrders() {
        log.info("Deleting unpaid orders");
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        List<Order> orders = orderRepository.findAll();
        List<Order> unpaidOrders = orders.stream()
                .filter(order -> !order.getIsPaid())
                .filter(order -> order.getModificationTime().isAfter(tenMinutesAgo))
                .toList();
        orderRepository.deleteAll(unpaidOrders);
        log.info("Deleted unpaid orders");
    }
}
