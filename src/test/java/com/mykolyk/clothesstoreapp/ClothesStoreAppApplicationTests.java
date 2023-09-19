package com.mykolyk.clothesstoreapp;

import com.mykolyk.clothesstoreapp.controller.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClothesStoreAppApplicationTests {
    @Value("http://localhost:${local.server.port}/api/v1/users/")  // SPeL
    private String baseUrl;

    @Value("${app.user.email}")
    private String email;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getUserTest() {
        UserModel userModel = testRestTemplate.getForObject(baseUrl + email, UserModel.class);
        assertEquals(userModel.getUserDto().getEmail(), email);
    }
}
