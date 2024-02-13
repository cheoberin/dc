package com.dc.accessservice.controller.impl;


import com.dc.accessservice.controller.AuthController;
import com.dc.accessservice.dto.income.RefreshTokenRequest;
import com.dc.accessservice.dto.income.SignInRequest;
import com.dc.accessservice.dto.income.SignUpRequest;
import com.dc.accessservice.dto.outcome.JwtAutheticationResponse;
import com.dc.accessservice.dto.outcome.UserResponseDto;
import com.dc.accessservice.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;

    public AuthControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    @PostMapping("signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.signUp(signUpRequest));
    }

    @Override
    @PostMapping("signin")
    public ResponseEntity<JwtAutheticationResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @Override
    @PostMapping("refresh")
    public ResponseEntity<JwtAutheticationResponse> refreshToken(@RequestBody @Valid RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
