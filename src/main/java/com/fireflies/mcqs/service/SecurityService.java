package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.User;

public interface SecurityService {
    String findLoggedInUsername();
    User findLoggedInUser();
    void autologin(String username, String password);
    boolean isUserAuthenticated();
}