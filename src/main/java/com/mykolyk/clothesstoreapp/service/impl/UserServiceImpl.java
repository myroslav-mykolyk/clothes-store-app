package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.exception.UserAlreadyExistException;
import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.mapping.UserMappingService;
import com.mykolyk.clothesstoreapp.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMappingService userMappingService;

    @Override
    public UserDto getUser(String email) {
        log.info("Getting user by email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        log.info("Founded user with email: {}", email);
        return userMappingService.mapUserToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        log.info("Creating user with email: {}", userDto.getEmail());
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserAlreadyExistException();
        }
        User user = userMappingService.mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        log.info("Created user with email: {}", user.getEmail());
        return userMappingService.mapUserToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("Updating user with email: {}", email);
        User persistedUser = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        persistedUser = userMappingService.populateUserWithPresentUserDtoFields(persistedUser, userDto);
        User storedUser = userRepository.save(persistedUser);
        log.info("Updated user with email: {}", storedUser.getEmail());
        return userMappingService.mapUserToUserDto(persistedUser);
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        log.info("Deleting user with email: {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
        log.info("Deleted user with email: {}", email);
    }
}
