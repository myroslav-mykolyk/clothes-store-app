package com.mykolyk.clothesstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodDto {
    @NotBlank(message = "'name' field should not be empty", groups = OnCreate.class)
    private String name;

    @Null(message = "'article' field should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'article' field should not be null", groups = OnCreate.class)
    private String article;

    @NotNull(message = "'price' field should not be null", groups = OnCreate.class)
    @Positive(message = "'price' field should be positive number", groups = OnUpdate.class)
    private Double price;

    @NotNull(message = "'quantity' field should not be null", groups = OnCreate.class)
    @PositiveOrZero(message = "'quantity' field should not be negative", groups = OnUpdate.class)
    private Integer quantity;
}
