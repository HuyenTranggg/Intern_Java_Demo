package com.example.thymeleaf.service.impl;

import com.example.thymeleaf.entity.User;
import com.example.thymeleaf.repository.UserRepository;
import com.example.thymeleaf.service.UserService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("#username == authentication.name or hasRole('ADMIN')")
    public String getUserProfile(String username) {
        User user = findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng: " + username));
        
        return "Đây là profile của " + user.getUsername();
    }
}