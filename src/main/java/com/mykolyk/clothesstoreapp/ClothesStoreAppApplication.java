package com.mykolyk.clothesstoreapp;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class ClothesStoreAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClothesStoreAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoUser(UserService userService,
                                      @Value("${app.user.password}") String password,
                                      @Value("${app.user.email}") String email) {
        return args -> {
            UserDto userDto = UserDto.builder()
                    .firstName("TestUserFirstName")
                    .lastName("TestUserLastName")
                    .email(email)
                    .password(password)
                    .repeatPassword(password)
                    .build();
            log.info("Creating default user with email: {}", email);
            userService.createUser(userDto);
        };
    }
}
