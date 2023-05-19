package com.mykolyk.clothesstoreapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @NotBlank(message = "'firstName' field should not be empty", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "'lastName' field should not empty", groups = OnCreate.class)
    private String lastName;

    @Email
    @Null(message = "'email' field should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'email' should not be empty", groups = OnCreate.class)
    private String email;

    @Null(message = "'password' field should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'password' field should not be empty", groups = OnCreate.class)
    private String password;

    @Null(message = "'repeatPassword' field should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "'repeatPassword' field should not be empty", groups = OnCreate.class)
    private String repeatPassword;
}
