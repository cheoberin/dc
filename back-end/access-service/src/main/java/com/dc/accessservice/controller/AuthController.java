package com.dc.accessservice.controller;

import com.dc.accessservice.dto.income.RefreshTokenRequest;
import com.dc.accessservice.dto.income.SignInRequest;
import com.dc.accessservice.dto.income.SignUpRequest;
import com.dc.accessservice.dto.outcome.JwtAutheticationResponse;
import com.dc.accessservice.dto.outcome.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {

    ResponseEntity<UserResponseDto> signUp(SignUpRequest signUpRequest);

    ResponseEntity<JwtAutheticationResponse> signIn(SignInRequest signInRequest);

    ResponseEntity<JwtAutheticationResponse> refreshToken(RefreshTokenRequest refreshTokenRequest);
}
