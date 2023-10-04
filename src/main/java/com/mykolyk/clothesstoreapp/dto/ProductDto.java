package com.mykolyk.clothesstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    @NotBlank(message = "'name' field should not be empty", groups = OnCreate.class)
    private String name;

    @Null(message = "'article' field should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'article' field should not be null", groups = OnCreate.class)
    private String article;

    @NotNull(message = "'price' field should not be null", groups = OnCreate.class)
    private BigDecimal price;
}
