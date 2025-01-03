package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime appointmentDate;
    private String status;

    // Πολλά appointments -> 1 citizen
    @ManyToOne
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    public Appointment() {}

    public Appointment(LocalDateTime appointmentDate, String status, Citizen citizen) {
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.citizen = citizen;
    }

    // -- Getters & Setters
    public Long getId() { return id; }
    public LocalDateTime getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDateTime appointmentDate) { this.appointmentDate = appointmentDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Citizen getCitizen() { return citizen; }
    public void setCitizen(Citizen citizen) { this.citizen = citizen; }
}