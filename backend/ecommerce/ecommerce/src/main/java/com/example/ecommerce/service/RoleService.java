package com.example.ecommerce.service;

import com.example.ecommerce.model.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
}
