package com.mykolyk.clothesstoreapp.test.util;

import com.mykolyk.clothesstoreapp.dto.GoodDto;
import com.mykolyk.clothesstoreapp.dto.OrderDto;
import com.mykolyk.clothesstoreapp.dto.OrderItemDto;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.model.Good;
import com.mykolyk.clothesstoreapp.model.Order;
import com.mykolyk.clothesstoreapp.model.OrderItem;
import com.mykolyk.clothesstoreapp.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestDataUtil {
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "password";

    private static final String ROLE = "ADMIN";

    public static final String NAME = "NAME";

    public static final String ARTICLE = "ARTICLE";

    public static String createUserDtoJson = "{\"firstName\":\"Myroslav\",\"lastName\":\"Mykolyk\",\"email\":\"MyroslavMykolyk@gmail.com\",\"role\":\"USER\",\"password\":\"ClothesStoreAppPass2023\",\"repeatPassword\":\"ClothesStoreAppPass2023\"}";
    public static String updateUserDtoJson = "{\"firstName\":\"Myroslav\",\"lastName\":\"Mykolyk\"}";


    public static User createUser() {
        return User.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .role(ROLE)
                .password(PASSWORD)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .role(ROLE)
                .password(PASSWORD)
                .repeatPassword(PASSWORD)
                .build();
    }
    public static Good createGood() {
        return Good.builder()
                .name(NAME)
                .article(ARTICLE)
                .price(BigDecimal.valueOf(1000))
                .quantity(100)
                .build();
    }

    public static GoodDto createGoodDto() {
        return GoodDto.builder()
                .name(NAME)
                .article(ARTICLE)
                .price(BigDecimal.valueOf(1000))
                .quantity(100)
                .build();
    }

    public static OrderItem createOrderItem() {
        return OrderItem.builder()
                .id(1L)
                .good(createGood())
                .quantity(10)
                .build();
    }

    public static OrderItemDto createOrderItemDto() {
        return OrderItemDto.builder()
                .id(1L)
                .goodArticle(ARTICLE)
                .quantity(10)
                .build();
    }

    public static Order createOrder() {
        return Order.builder()
                .id(1L)
                .user(createUser())
                .orderItems(Collections.singletonList(createOrderItem()))
                .creationTime(LocalDateTime.now())
                .modificationTime(LocalDateTime.now())
                .isPaid(false)
                .build();
    }

    public static OrderDto createOrderDto() {
        return OrderDto.builder()
                .id(1L)
                .userEmail(EMAIL)
                .orderItemsIds(Collections.singletonList(1L))
                .creationTime(LocalDateTime.now())
                .creationTime(LocalDateTime.now())
                .isPaid(false)
                .build();
    }
}
