package com.mykolyk.clothesstoreapp.service;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.exception.UserAlreadyExistsException;
import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.impl.MappingServiceImpl;
import com.mykolyk.clothesstoreapp.service.impl.UserServiceImpl;
import com.mykolyk.clothesstoreapp.test.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.mykolyk.clothesstoreapp.test.util.TestDataUtil.TEST_EMAIL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private final MappingService mappingService = new MappingServiceImpl();
    @Mock
    private UserRepository userRepository;

    @Test
    void getUserTest() {
        User user = TestDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getUser(TEST_EMAIL);

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstName", equalTo(user.getFirstName()))
        ));
    }

    @Test
    void getUserUserNotFoundTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(TEST_EMAIL));
    }

    @Test
    public void createUserTest() {
        User testUser = TestDataUtil.createUser();
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.createUser(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("firstName", equalTo(testUser.getFirstName())),
                hasProperty("lastName", equalTo(testUser.getLastName())),
                hasProperty("email", equalTo(testUser.getEmail()))
        ));
    }

    @Test
    public void createUserUserAlreadyExistsTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.existsByEmail(TEST_EMAIL)).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userService.createUser(testUserDto));
    }

    @Test
    public void updateUserTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();
        User testUser = TestDataUtil.createUser();
        when(userRepository.findByEmail(testUserDto.getEmail())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.updateUser(testUser.getEmail(), testUserDto);

        assertThat(userDto, allOf(
                hasProperty("firstName", equalTo(testUser.getFirstName())),
                hasProperty("lastName", equalTo(testUser.getLastName())),
                hasProperty("email", equalTo(testUser.getEmail()))
        ));
    }

    @Test
    public void updateUserUserNotFoundTest() {
        UserDto testUserDto = TestDataUtil.createUserDto();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(testUserDto.getEmail(), testUserDto));
    }

    @Test
    void deleteUserTest() {
        User testUser = TestDataUtil.createUser();
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getEmail());

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUserUserNotFoundTest() {
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(TEST_EMAIL));
    }
}
