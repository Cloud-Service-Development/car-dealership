package com.example.car_dealership.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int afm;
    private String email;
    private String password;
    private String username;
    @Column(name="contacting")
    private String contactInfo;

    // -- Constructors
    public User() {}

    public User(int afm,String email, String password, String username, String contactInfo) {
        this.afm = afm;
        this.email = email;
        this.password = password;
        this.username = username;
        this.contactInfo = contactInfo;
    }

    // -- Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAfm() { return afm; }
    public void setAfm(int afm) { this.afm = afm; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getContactInfo() {return contactInfo;}
    public void setContactInfo(String contactInfo) {this.contactInfo = contactInfo;}
}