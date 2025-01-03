package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;
}
