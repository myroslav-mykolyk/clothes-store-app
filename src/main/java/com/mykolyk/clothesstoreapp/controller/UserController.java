package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/users/{email}")
    public UserDto getUser(@PathVariable String email) {
        return userService.getUser(email);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/users/{email}")
    public UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @DeleteMapping(value = "/users/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
