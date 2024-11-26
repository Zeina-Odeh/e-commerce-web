package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.RegistrationRequest;
import com.example.ecommerce.dto.UserProfileUpdateDTO;
import com.example.ecommerce.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "username", target = "userName")
    @Mapping(source = "email", target = "userEmail")
    @Mapping(source = "password", target = "userPassword")
    @Mapping(source = "userPhoneNumber", target = "userPhoneNumber")
    @Mapping(source = "userAddress", target = "userAddress")
    @Mapping(target = "role", ignore = true)
    User toUser(RegistrationRequest request);


    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "userName", source = "name")
    @Mapping(target = "userEmail", source = "email")
    @Mapping(target = "userPhoneNumber", source = "phoneNumber")
    @Mapping(target = "userAddress", source = "address")
    void fromDto(UserProfileUpdateDTO dto, @MappingTarget User entity);

    RegistrationRequest toRegistrationRequest(User user);

    UserProfileUpdateDTO toUserProfileDTO(User user);
}
