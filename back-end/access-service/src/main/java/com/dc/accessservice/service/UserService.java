package com.dc.accessservice.service;

import com.dc.accessservice.dto.outcome.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    boolean isEmailAvailable(String email);

    UserResponseDto getUser(UUID id);
}
