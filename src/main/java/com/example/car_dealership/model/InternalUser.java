package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Schema(description = "Entity representing an internal user in the system.")
public class InternalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the user.", example = "1")
    private int id;

    @Schema(description = "The username of the user.", example = "john_doe")
    private String username;

    @Schema(description = "The email address of the user.", example = "johndoe@example.com")
    private String email;

    @Schema(description = "The hashed password of the user.", example = "$2a$10$hashedpassword")
    private String password;

    @Schema(
            description = "The role of the user within the system. Roles can include 'customer', 'dealership', or 'admin'.",
            example = "customer"
    )
    private String role;

    protected InternalUser() {}

    public InternalUser(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}