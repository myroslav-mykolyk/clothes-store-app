package com.mykolyk.clothesstoreapp.controller;

import com.mykolyk.clothesstoreapp.controller.assembler.UserAssembler;
import com.mykolyk.clothesstoreapp.controller.model.UserModel;
import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.UserService;
import com.mykolyk.clothesstoreapp.test.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.mykolyk.clothesstoreapp.test.util.TestDataUtil.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@Import(TestConfig.class)
public class UserControllerTest {
    @MockBean
    private UserService userService;

    @MockBean
    private UserAssembler userAssembler;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUserTest() throws Exception {
        UserDto userDto = createUserDto();
        UserModel userModel = new UserModel(userDto);

        when(userService.getUser(EMAIL)).thenReturn(userDto);
        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(get("/api/v1/users/" + EMAIL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(EMAIL));
    }

    @Test
    void createUserTest() throws Exception {
        UserDto userDto = createUserDto();
        UserModel userModel = new UserModel(userDto);

        when(userService.createUser(userDto)).thenReturn(userDto);
        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserDtoJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void updateUserTest() throws Exception {
        UserDto userDto = createUserDto();
        UserModel userModel = new UserModel(userDto);

        when(userService.updateUser(EMAIL, userDto)).thenReturn(userDto);
        when(userAssembler.toModel(userDto)).thenReturn(userModel);

        mockMvc.perform(patch("/api/v1/users/" + EMAIL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserDtoJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/v1/users/" + EMAIL))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
