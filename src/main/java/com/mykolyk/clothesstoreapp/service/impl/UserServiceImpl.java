package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String email) {
        log.info("Getting user by email: {}", email);
        User user = userRepository.getUser(email);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user with email: {}", userDto.getEmail());
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("Updating user with email: {}", email);
        User user = mapUserDtoToUser(userDto);

        User oldUser = userRepository.getUser(email);
        user.setEmail(oldUser.getEmail());
        user.setPassword(oldUser.getPassword());

        user = userRepository.updateUser(email, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(String email) {
        log.info("Deleting user with email: {}", email);
        userRepository.deleteUser(email);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
