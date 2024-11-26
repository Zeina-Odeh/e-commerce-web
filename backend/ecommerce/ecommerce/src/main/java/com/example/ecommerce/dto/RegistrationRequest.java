package com.example.ecommerce.dto;

import com.example.ecommerce.component.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "Name is mandatory")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @ValidPassword
    private String password;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Invalid phone number")
    private String userPhoneNumber;

    @NotBlank(message = "Address is mandatory")
    private String userAddress;

    private LocalDateTime creationDate;

}
