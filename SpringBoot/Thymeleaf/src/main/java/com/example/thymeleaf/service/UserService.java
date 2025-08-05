package com.example.thymeleaf.service;

import com.example.thymeleaf.entity.User;
import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    User save(User user);

    String getUserProfile(String username);
}