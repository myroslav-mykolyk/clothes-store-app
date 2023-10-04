package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.api.UserApi;
import com.mykolyk.clothesstoreapp.controller.assembler.UserAssembler;
import com.mykolyk.clothesstoreapp.controller.model.UserModel;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserAssembler userAssembler;
    private final UserService userService;

    @Override
    public UserModel getUser(String email) {
        UserDto userDto = userService.getUser(email);
        return userAssembler.toModel(userDto);
    }

    @Override
    public UserModel createUser(UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public UserModel updateUser(String email, UserDto userDto) {
        UserDto outUserDto = userService.updateUser(email, userDto);
        return userAssembler.toModel(outUserDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
