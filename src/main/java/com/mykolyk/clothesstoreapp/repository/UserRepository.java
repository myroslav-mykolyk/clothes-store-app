package com.mykolyk.clothesstoreapp.repository;

import com.mykolyk.clothesstoreapp.model.User;

public interface UserRepository {
    User getUser(String email);

    User createUser(User user);

    User updateUser(String email, User user);

    void deleteUser(String email);
}
