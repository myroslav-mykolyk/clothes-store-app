package com.mykolyk.clothesstoreapp.controller.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mykolyk.clothesstoreapp.dto.GoodDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class GoodModel extends RepresentationModel<GoodModel> {
    @JsonUnwrapped
    private GoodDto goodDto;
}
