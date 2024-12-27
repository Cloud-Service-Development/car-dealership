package com.example.model;

import jakarta.persistence.*;

import java.sql.ConnectionBuilder;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Θα είναι hashed
    private String email;

    @Enumerated
    private String role; // Customer, Admin, Dealership

    public static ConnectionBuilder withUsername(String admin) {
        return null;
    }

    // Getters και Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}