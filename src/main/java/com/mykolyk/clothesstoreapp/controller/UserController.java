package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.assembler.UserAssembler;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnCreate;
import com.mykolyk.clothesstoreapp.dto.validation.groups.OnUpdate;
import com.mykolyk.clothesstoreapp.model.UserModel;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserAssembler userAssembler;
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{email}")
    public UserModel getUser(@PathVariable String email) {
        UserDto outUserDto = userService.getUser(email);
        return userAssembler.toModel(outUserDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public UserModel createUser(@RequestBody @Validated(OnCreate.class)  UserDto userDto) {
        UserDto outUserDto = userService.createUser(userDto);
        return userAssembler.toModel(outUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{email}")
    public UserModel updateUser(@PathVariable String email, @RequestBody @Validated(OnUpdate.class) UserDto userDto) {
        UserDto outUserDto = userService.updateUser(email, userDto);
        return userAssembler.toModel(outUserDto);
    }

    @DeleteMapping(value = "/users/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
