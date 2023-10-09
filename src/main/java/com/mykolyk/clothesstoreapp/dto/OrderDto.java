package com.mykolyk.clothesstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Long id;

    @NotBlank(message = "'userEmail' field should not be empty", groups = OnCreate.class)
    private String userEmail;

    @NotEmpty(message = "'orderItemsIds' field should not be empty", groups = OnCreate.class)
    private List<Long> orderItemsIds;

    @Null(message = "'creationTime' field should be absent in request", groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime creationTime;

    @Null(message = "'modificationTime' field should be absent in request", groups = {OnCreate.class, OnUpdate.class})
    private LocalDateTime modificationTime;

    @Null(message = "'isPaid' field should be absent in request", groups = {OnCreate.class, OnUpdate.class})
    private Boolean isPaid;
}
