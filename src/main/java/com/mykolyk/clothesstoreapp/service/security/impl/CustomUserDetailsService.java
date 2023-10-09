package com.mykolyk.clothesstoreapp.service.security.impl;

import com.mykolyk.clothesstoreapp.dto.UserDto;
import com.mykolyk.clothesstoreapp.exception.UserNotFoundException;
import com.mykolyk.clothesstoreapp.model.User;
import com.mykolyk.clothesstoreapp.repository.UserRepository;
import com.mykolyk.clothesstoreapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
        org.springframework.security.core.userdetails.User.UserBuilder builder = withUsername(username);
        builder.password(user.getPassword());
        builder.roles(user.getRole());
        return builder.build();
    }
}
