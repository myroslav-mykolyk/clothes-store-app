package com.mykolyk.clothesstoreapp.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mykolyk.clothesstoreapp.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderModel extends RepresentationModel<OrderModel> {
    @JsonUnwrapped
    private OrderDto orderDto;
}