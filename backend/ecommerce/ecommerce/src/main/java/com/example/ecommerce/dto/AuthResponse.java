package com.example.ecommerce.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private Date expirationDate;
    private String username;
    private String email;
    private String userPhoneNumber;
    private String userAddress;
    private String[] roles;
    private Long userId;

    public AuthResponse(String token, Date expirationDate, String username, String email,
                        String userPhoneNumber, String userAddress, String[] roles, Long userId) {
        this.token = token;
        this.expirationDate = expirationDate;
        this.username = username;
        this.email = email;
        this.userPhoneNumber = userPhoneNumber;
        this.userAddress = userAddress;
        this.roles = roles;
        this.userId = userId;
    }

    public AuthResponse(String jwtToken) {
        this.token = jwtToken;
    }
}
