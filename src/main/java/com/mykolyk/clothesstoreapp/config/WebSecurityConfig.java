package com.mykolyk.clothesstoreapp.config;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.service.UserService;
import com.mykolyk.clothesstoreapp.service.security.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


//TODO: Fix security configuration!
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/goods").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/users", "/api/v1/orders", "/api/v1/order_items")/*.hasAnyRole(ROLE_ADMIN, ROLE_USER)*/.permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/goods")/*.hasRole(ROLE_ADMIN)*/.permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/orders", "/api/v1/order_items")/*.hasAnyRole(ROLE_ADMIN, ROLE_USER)*/.permitAll()
                .requestMatchers(HttpMethod.PATCH, "/api/v1/goods/**")/*.hasRole(ROLE_ADMIN)*/.permitAll()
                .requestMatchers(HttpMethod.PATCH, "/api/v1/users/**", "/api/v1/orders/**", "/api/v1/orders/**/pay", "/api/v1/order_items/**")/*.hasAnyRole(ROLE_ADMIN, ROLE_USER)*/.permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**", "/api/v1/goods/**", "/api/v1/goods/orders", "/api/v1/order_items/**")/*.hasRole(ROLE_ADMIN)*/.permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDto admin = UserDto.builder()
                .firstName("Admin")
                .lastName("Admin")
                .email("Admin@admin.com")
                .role(ROLE_ADMIN)
                .password("adminPassword")
                .repeatPassword("adminPassword")
                .build();
        userService.createUser(admin);

        UserDetails adminUserDetails = customUserDetailsService.loadUserByUsername("Admin@admin.com");
        return new InMemoryUserDetailsManager(adminUserDetails);
    }
}
