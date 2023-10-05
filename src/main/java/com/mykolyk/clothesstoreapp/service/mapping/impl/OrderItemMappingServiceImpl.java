package com.mykolyk.clothesstoreapp.service.mapping.impl;

import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.exception.GoodNotFoundException;
import com.mykolyk.clothesstoreapp.model.Good;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.repository.GoodRepository;
import com.mykolyk.clothesstoreapp.repository.OrderItemRepository;
import com.mykolyk.clothesstoreapp.service.mapping.OrderItemMappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderItemMappingServiceImpl implements OrderItemMappingService {
    private final GoodRepository goodRepository;

    @Override
    public OrderItemDto mapOrderItemToOrderItemDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .goodArticle(orderItem.getGood().getArticle())
                .quantity(orderItem.getQuantity())
                .build();
    }

    @Override
    public OrderItem mapOrderItemDtoToOrderItem(OrderItemDto orderItemDto) {
        return OrderItem.builder()
                .id(orderItemDto.getId())
                .good(goodRepository.findByArticle(orderItemDto.getGoodArticle()).orElseThrow(GoodNotFoundException::new))
                .quantity(orderItemDto.getQuantity())
                .build();
    }

    @Override
    public OrderItem populateOrderItemWithPresentOrderItemDtoFields(OrderItem orderItem, OrderItemDto orderItemDto) {
        Integer quantity = orderItemDto.getQuantity();
        if(Objects.nonNull(quantity)) {
            orderItem.setQuantity(quantity);
        }
        return  orderItem;
    }
}
