package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Citizen")
public class Citizen extends User {

    private String name;
    private String location;
    private String contacting;

    // Παράδειγμα OneToMany: ένας Citizen μπορεί να έχει πολλά Appointments
    @OneToMany(mappedBy="citizen", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    // -- Constructors
    public Citizen() {
        super();
    }

    public Citizen(String email, String password, String username,
                   String name, String location, String contacting) {
        super(email, password, username);
        this.name = name;
        this.location = location;
        this.contacting = contacting;
    }

    // -- Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContacting() { return contacting; }
    public void setContacting(String contacting) { this.contacting = contacting; }

    public List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}