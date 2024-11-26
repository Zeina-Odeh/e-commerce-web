package com.example.ecommerce.service;

import com.example.ecommerce.dto.RegistrationRequest;
import com.example.ecommerce.dto.UserProfileUpdateDTO;
import com.example.ecommerce.model.User;

public interface UserService {
    User registerUser(RegistrationRequest registrationRequest);
    User updateUserProfile(Long id, UserProfileUpdateDTO userProfileUpdateDTO);
    User findByEmail(String email);
    boolean checkIfEmailExists(String email);
}
