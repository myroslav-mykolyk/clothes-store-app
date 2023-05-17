package com.mykolyk.clothesstoreapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
}
