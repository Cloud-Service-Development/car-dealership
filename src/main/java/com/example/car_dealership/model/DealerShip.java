package com.example.car_dealership.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class DealerShip extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String contactInfo;

    @OneToMany(mappedBy = "dealership", cascade = CascadeType.ALL)
    private List<Car> cars;
}