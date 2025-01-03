package com.example.car_dealership.model;

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

    @Enumerated(EnumType.STRING)
    private UserRole role; // Customer, Admin, Dealership

    public static ConnectionBuilder withUsername(String admin) {
        return null;
    }

    public enum UserRole {
        ADMIN,
        CUSTOMER,
        DEALERSHIP
    }
}