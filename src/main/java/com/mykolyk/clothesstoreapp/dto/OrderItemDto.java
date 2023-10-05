package com.mykolyk.clothesstoreapp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemDto {
    private Long id;

    private String goodArticle;

    private Integer quantity;
}
