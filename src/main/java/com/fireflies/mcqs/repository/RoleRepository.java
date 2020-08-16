package com.fireflies.mcqs.repository;


import com.fireflies.mcqs.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findAllByName(String Name);
}