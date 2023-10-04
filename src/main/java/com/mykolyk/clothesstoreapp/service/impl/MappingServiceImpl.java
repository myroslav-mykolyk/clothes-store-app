package com.mykolyk.clothesstoreapp.service.impl;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.exception.PasswordsNotValidException;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.service.MappingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MappingServiceImpl implements MappingService {
    @Override
    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(extractPassword(userDto))
                .build();
    }

    @Override
    public User populateUserWithPresentUserDtoFields(User user, UserDto userDto) {
        String firstName = userDto.getFirstName();
        if (Objects.nonNull(firstName)) {
            user.setFirstName(firstName);
        }
        String lastName = userDto.getLastName();
        if (Objects.nonNull(lastName)) {
            user.setLastName(lastName);
        }
        return user;
    }

    private String extractPassword(UserDto userDto) {
        if (StringUtils.isNotBlank(userDto.getPassword()) &&
                StringUtils.equals(userDto.getPassword(), userDto.getRepeatPassword())) {
            return userDto.getPassword();
        } else {
            throw new PasswordsNotValidException();
        }
    }
}