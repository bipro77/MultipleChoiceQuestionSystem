package com.fireflies.mcqs.service;




import com.fireflies.mcqs.model.Role;
import com.fireflies.mcqs.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
    		roleRepository.save(role);
    }


	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

    @Override
    public Role findAllByName(String name) {
        return roleRepository.findAllByName(name);
    }


}
