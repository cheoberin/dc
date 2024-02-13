package com.dc.accessservice.mapper;


import com.dc.accessservice.dto.income.SignUpRequest;
import com.dc.accessservice.dto.outcome.UserResponseDto;
import com.dc.accessservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        return user;
    }

    public UserResponseDto toUserDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRoles()
        );
    }

}
