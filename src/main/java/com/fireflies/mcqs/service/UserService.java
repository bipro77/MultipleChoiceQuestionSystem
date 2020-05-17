package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);
}