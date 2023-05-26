package com.mykolyk.clothesstoreapp.model;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserModel extends RepresentationModel<UserModel> {
    @JsonUnwrapped
    private UserDto userDto;
}
