package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.RegistrationRequest;
import com.example.ecommerce.dto.UserProfileUpdateDTO;
import com.example.ecommerce.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-10T02:39:32-0800",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(RegistrationRequest request) {
        if ( request == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( request.getUsername() );
        user.setUserEmail( request.getEmail() );
        user.setUserPassword( request.getPassword() );
        user.setUserPhoneNumber( request.getUserPhoneNumber() );
        user.setUserAddress( request.getUserAddress() );
        user.setCreationDate( request.getCreationDate() );

        return user;
    }

    @Override
    public void fromDto(UserProfileUpdateDTO dto, User entity) {
        if ( dto == null ) {
            return;
        }

        entity.setUserName( dto.getName() );
        entity.setUserEmail( dto.getEmail() );
        entity.setUserPhoneNumber( dto.getPhoneNumber() );
        entity.setUserAddress( dto.getAddress() );
    }

    @Override
    public RegistrationRequest toRegistrationRequest(User user) {
        if ( user == null ) {
            return null;
        }

        RegistrationRequest registrationRequest = new RegistrationRequest();

        registrationRequest.setUserPhoneNumber( user.getUserPhoneNumber() );
        registrationRequest.setUserAddress( user.getUserAddress() );
        registrationRequest.setCreationDate( user.getCreationDate() );

        return registrationRequest;
    }

    @Override
    public UserProfileUpdateDTO toUserProfileDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserProfileUpdateDTO userProfileUpdateDTO = new UserProfileUpdateDTO();

        return userProfileUpdateDTO;
    }
}
