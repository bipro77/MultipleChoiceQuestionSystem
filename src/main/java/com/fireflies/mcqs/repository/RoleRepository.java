package com.fireflies.mcqs.repository;


import com.fireflies.mcqs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}