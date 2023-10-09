package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.GoodService;
import com.mykolyk.clothesstoreapp.service.OrderItemService;
import com.mykolyk.clothesstoreapp.service.OrderService;
import com.mykolyk.clothesstoreapp.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataInitializerService {
    private final UserService userService;
    private final GoodService goodService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    @PostConstruct
    public void inject() {
        UserDto user = UserDto.builder()
                .firstName("User")
                .lastName("User")
                .email("User@user.com")
                .role("USER")
                .password("userPassword")
                .repeatPassword("userPassword")
                .build();
        userService.createUser(user);

        GoodDto tShirt = GoodDto.builder()
                .name("T-Shirt")
                .article("C-00001")
                .price(BigDecimal.valueOf(349.00))
                .quantity(978)
                .build();
        goodService.createGood(tShirt);

        GoodDto shirt = GoodDto.builder()
                .name("Shirt")
                .article("C-00002")
                .price(BigDecimal.valueOf(599.00))
                .quantity(854)
                .build();
        goodService.createGood(shirt);

        OrderItemDto orderItem1 = OrderItemDto.builder()
                .id(1L)
                .goodArticle("C-00001")
                .quantity(3)
                .build();
        orderItemService.createOrderItem(orderItem1);

        OrderItemDto orderItem2 = OrderItemDto.builder()
                .id(2L)
                .goodArticle("C-00002")
                .quantity(3)
                .build();
        orderItemService.createOrderItem(orderItem2);

        OrderDto order1 = OrderDto.builder()
                .id(1L)
                .userEmail("User@user.com")
                .orderItemsIds(List.of(orderItem1.getId(), orderItem2.getId()))
                .build();
        orderService.createOrder(order1);
    }
}
