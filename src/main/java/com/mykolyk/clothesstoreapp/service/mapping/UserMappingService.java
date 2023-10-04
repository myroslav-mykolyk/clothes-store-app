package com.mykolyk.clothesstoreapp.service.mapping;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.model.User;

public interface UserMappingService {
    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    User populateUserWithPresentUserDtoFields(User user, UserDto userDto);
}
