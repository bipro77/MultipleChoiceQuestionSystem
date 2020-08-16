package com.fireflies.mcqs.service;

import com.fireflies.mcqs.model.Role;

import java.util.List;



public interface RoleService {
    void save(Role role);
    
    List<Role>findAll();

    Role findAllByName(String Name);
}
