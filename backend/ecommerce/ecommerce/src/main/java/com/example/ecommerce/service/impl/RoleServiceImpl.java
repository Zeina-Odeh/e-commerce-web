package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role Not Found"));
    }
}
