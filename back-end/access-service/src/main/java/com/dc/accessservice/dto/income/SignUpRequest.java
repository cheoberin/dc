package com.dc.accessservice.dto.income;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class SignUpRequest {

    @NotBlank(message = "Name must not be null")
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email must not be null")
    private String email;

    @Size(min = 8, message = "Password must have at least 8 characters")
    @NotBlank(message = "Password must not be null")
    @Pattern(regexp = "^(?=.*[a-zà-öø-ý])[\\s\\S]*$", message = "Password must have at least 1 lower-case letter")
    @Pattern(regexp = "^(?=.*[A-ZÀ-ÖØ-Ý])[\\s\\S]*$", message = "Password must have at least 1 upper-case letter")
    @Pattern(regexp = "^(?=.*\\d)[\\s\\S]*$", message = "Password must have at least 1 number")
    @Pattern(regexp = "^(?=.*[!@#$%^&()-+=])[\\s\\S]*$", message = "Password must have at least 1 special character")
    private String password;

    public SignUpRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
