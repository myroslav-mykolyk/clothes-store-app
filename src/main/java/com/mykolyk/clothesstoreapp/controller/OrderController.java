package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.api.OrderApi;
import com.mykolyk.clothesstoreapp.controller.assembler.OrderAssembler;
import com.mykolyk.clothesstoreapp.controller.model.OrderModel;
import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    private final OrderAssembler orderAssembler;
    private final OrderService orderService;

    @Override
    public OrderModel getOrder(Long id) {
        OrderDto OrderDto = orderService.getOrder(id);
        return orderAssembler.toModel(OrderDto);
    }

    @Override
    public OrderModel createOrder(OrderDto OrderDto) {
        OrderDto outOrderDto = orderService.createOrder(OrderDto);
        return orderAssembler.toModel(outOrderDto);
    }

    @Override
    public OrderModel updateOrder(Long id, OrderDto OrderDto) {
        OrderDto outOrderDto = orderService.updateOrder(id, OrderDto);
        return orderAssembler.toModel(outOrderDto);
    }

    @Override
    public OrderModel payForOrder(Long id) {
        OrderDto orderDto = orderService.payFoOrder(id);
        return orderAssembler.toModel(orderDto);
    }

    @Override
    public ResponseEntity<Void> deleteOrder(Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
