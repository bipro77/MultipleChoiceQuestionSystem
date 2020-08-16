package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.Role;
import com.fireflies.mcqs.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    List<String> findAllUsernames();

    User findAllById(Integer parseInt);

    List<User> findAllByRoles(Role role);

    void delete(User user);
}