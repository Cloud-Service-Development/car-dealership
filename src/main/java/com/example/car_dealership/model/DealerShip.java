package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("DealerShip")
public class DealerShip extends User {

    private String name;
    private String location;
    private String contactInfo;

    // Παράδειγμα OneToMany: ένα DealerShip μπορεί να έχει πολλά Cars
    @OneToMany(mappedBy="dealerShip", cascade = CascadeType.ALL)
    private List<Car> cars;

    //-- Constructors
    public DealerShip() {super();}

    public DealerShip(String email, String password, String username,
                      String name, String location, String contactInfo) {
        super(email, password, username);
        this.name = name;
        this.location = location;
        this.contactInfo= contactInfo;
    }

    // -- Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contacting) { this.contactInfo = contacting; }

    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) {this.cars = cars;}
}