package com.mykolyk.clothesstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private Long id;

    private String userEmail;

    private List<Long> orderItemsIds;

    private LocalDateTime creationTime;

    private LocalDateTime modificationTime;

    private boolean isPayed;
}
