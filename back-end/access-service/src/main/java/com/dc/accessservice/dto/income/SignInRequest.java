package com.dc.accessservice.dto.income;

import jakarta.validation.constraints.NotBlank;

public class SignInRequest {

    @NotBlank(message = "Email must be provided")
    private String email;
    @NotBlank(message = "Password must be provided")
    private String password;

    public SignInRequest() {
    }

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
