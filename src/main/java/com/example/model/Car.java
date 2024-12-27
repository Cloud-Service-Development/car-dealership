package com.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Double price;
    private Integer year;
    private String status;

    @ManyToOne
    @JoinColumn(name = "dealership_id")
    private DealerShip dealership;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Appointment> appointments;
}