package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.api.OrderItemApi;
import com.mykolyk.clothesstoreapp.controller.assembler.OrderItemAssembler;
import com.mykolyk.clothesstoreapp.controller.model.OrderItemModel;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderItemController implements OrderItemApi {
    private final OrderItemAssembler orderItemAssembler;
    private final OrderItemService orderItemService;

    @Override
    public OrderItemModel getOrderItem(Long id) {
        OrderItemDto OrderItemDto = orderItemService.getOrderItem(id);
        return orderItemAssembler.toModel(OrderItemDto);
    }

    @Override
    public OrderItemModel createOrderItem(OrderItemDto OrderItemDto) {
        OrderItemDto outOrderItemDto = orderItemService.createOrderItem(OrderItemDto);
        return orderItemAssembler.toModel(outOrderItemDto);
    }

    @Override
    public OrderItemModel updateOrderItem(Long id, OrderItemDto OrderItemDto) {
        OrderItemDto outOrderItemDto = orderItemService.updateOrderItem(id, OrderItemDto);
        return orderItemAssembler.toModel(outOrderItemDto);
    }

    @Override
    public ResponseEntity<Void> deleteOrderItem(Long id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }
}
