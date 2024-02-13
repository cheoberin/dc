package com.dc.accessservice.service.impl;


import com.dc.accessservice.dto.income.RefreshTokenRequest;
import com.dc.accessservice.dto.income.SignInRequest;
import com.dc.accessservice.dto.income.SignUpRequest;
import com.dc.accessservice.dto.outcome.JwtAutheticationResponse;
import com.dc.accessservice.dto.outcome.UserResponseDto;
import com.dc.accessservice.enums.Role;
import com.dc.accessservice.mapper.UserMapper;
import com.dc.accessservice.model.User;
import com.dc.accessservice.repository.UserRepository;
import com.dc.accessservice.service.AuthenticationService;
import com.dc.accessservice.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public UserResponseDto signUp(SignUpRequest signUpRequest) {
        User user = userMapper.toUser(signUpRequest);
        user.setRoles(Collections.singleton(Role.USER));
        user.setStatus(true);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserResponseDto signUpAdm(SignUpRequest signUpRequest) {
        User user = userMapper.toUser(signUpRequest);
        user.setRoles(Set.of(Role.ADMIN, Role.USER));
        user.setStatus(true);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public JwtAutheticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));

        User user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        return new JwtAutheticationResponse(jwt, refreshToken);
    }

    @Override
    public JwtAutheticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));

        if (jwtService.isTokenValid(refreshTokenRequest.getRefreshToken(), user)) {
            String jwt = jwtService.generateToken(user);
            String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            return new JwtAutheticationResponse(jwt, refreshToken);
        }
        return null;
    }

}