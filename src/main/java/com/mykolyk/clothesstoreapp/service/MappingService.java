package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.model.User;

public interface MappingService {

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);
}
