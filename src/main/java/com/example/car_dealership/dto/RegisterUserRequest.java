package com.example.car_dealership.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for registering a new user.")
public class RegisterUserRequest {

    @Schema(
            description = "The username of the user. Must be between 3 and 20 characters.",
            example = "john_doe"
    )
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Schema(
            description = "The tax number of the user. Must be alphanumeric and between 8 and 15 characters.",
            example = "ABC1234567"
    )
    @NotBlank(message = "Tax number is required")
    @Pattern(
            regexp = "^[A-Z0-9]{8,15}$",
            message = "Tax number must be alphanumeric and between 8 and 15 characters"
    )
    private String taxNumber;

    @Schema(
            description = "The email address of the user. Must be a valid email format.",
            example = "johndoe@example.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "The password for the user account. Must be between 8 and 20 characters long.",
            example = "password123"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
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