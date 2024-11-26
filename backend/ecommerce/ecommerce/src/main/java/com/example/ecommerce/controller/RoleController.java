package com.example.ecommerce.controller;

import com.example.ecommerce.model.Role;
import com.example.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{roleName}")
    public ResponseEntity<?> getRole(@PathVariable String roleName) {
        Role role = roleService.findByRoleName(roleName);
        return ResponseEntity.ok(role);
    }
}

