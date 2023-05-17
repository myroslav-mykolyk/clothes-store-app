package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.UserDto;

public interface UserService {
    UserDto getUser(String email);

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);
}
