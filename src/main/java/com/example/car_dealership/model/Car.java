package com.example.car_dealership.model;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Double price;

    @ManyToOne
    @JoinColumn(name="dealership_id")
    private DealerShip dealerShip;

    public Car() {}

    public Car(String brand, String model, Double price, DealerShip dealerShip) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.dealerShip = dealerShip;
    }

    // -- Getters & Setters
    public Long getId() { return id; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public DealerShip getDealerShip() { return dealerShip; }
    public void setDealerShip(DealerShip dealerShip) { this.dealerShip = dealerShip; }
}