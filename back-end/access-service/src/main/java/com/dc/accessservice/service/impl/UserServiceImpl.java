package com.dc.accessservice.service.impl;

import com.dc.accessservice.dto.outcome.UserResponseDto;
import com.dc.accessservice.mapper.UserMapper;
import com.dc.accessservice.model.User;
import com.dc.accessservice.repository.UserRepository;
import com.dc.accessservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Override
    public boolean isEmailAvailable(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user == null;
    }

    @Override
    public UserResponseDto getUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found: " + id));
        return userMapper.toUserDto(user);
    }

}