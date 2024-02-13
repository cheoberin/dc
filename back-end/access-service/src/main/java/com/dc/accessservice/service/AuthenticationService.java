package com.dc.accessservice.service;

import com.dc.accessservice.dto.income.RefreshTokenRequest;
import com.dc.accessservice.dto.income.SignInRequest;
import com.dc.accessservice.dto.income.SignUpRequest;
import com.dc.accessservice.dto.outcome.JwtAutheticationResponse;
import com.dc.accessservice.dto.outcome.UserResponseDto;

public interface AuthenticationService {
    UserResponseDto signUp(SignUpRequest signUpRequest);
    UserResponseDto signUpAdm(SignUpRequest signUpRequest);
    JwtAutheticationResponse signIn(SignInRequest signInRequest);
    JwtAutheticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}