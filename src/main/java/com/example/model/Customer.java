package com.example.model;

import com.example.model.Appointment;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer extends User {
    @OneToMany(mappedBy = "customer")
    private List<Appointment> appointments;
}
