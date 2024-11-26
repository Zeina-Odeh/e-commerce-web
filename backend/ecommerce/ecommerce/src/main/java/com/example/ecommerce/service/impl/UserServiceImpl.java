package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.UserProfileUpdateDTO;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.model.Role;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.ecommerce.dto.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.ecommerce.exception.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

     private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public User registerUser(RegistrationRequest request) {

        User user = userMapper.toUser(request);

        String hashedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(hashedPassword);

        Role defaultRole = new Role();
        defaultRole.setRoleId(1L);
        defaultRole.setRoleName("customer");
        user.setRole(defaultRole);
        return userRepository.save(user);

    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    public boolean checkIfEmailExists(String userEmail) {
        return userRepository.existsByUserEmail(userEmail);
    }

    @Override
    @Transactional
    public User updateUserProfile(Long userId, UserProfileUpdateDTO userProfileUpdateDTO) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        userMapper.fromDto(userProfileUpdateDTO, existingUser);

        return userRepository.save(existingUser);

    }

}
