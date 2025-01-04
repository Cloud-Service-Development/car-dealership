package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("DealerShip")
public class DealerShip extends User {

    private String name;
    private String location;

    // Παράδειγμα OneToMany: ένα DealerShip μπορεί να έχει πολλά Cars
    @OneToMany(mappedBy="dealerShip", cascade = CascadeType.ALL)
    private List<Car> cars;

    //-- Constructors
    public DealerShip() {super();}

    public DealerShip(int afm,String email, String password, String username,String contactInfo,
                      String name, String location) {
        super(afm,email, password, username,contactInfo);
        this.name = name;
        this.location = location;
    }

    // -- Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) {this.cars = cars;}
}