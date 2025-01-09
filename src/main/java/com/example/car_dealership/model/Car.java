package com.example.car_dealership.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;
    private String model;
    private String fuelType;
    private String engine;
    private int seats;
    private int price;
    private String additionalInfo;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name="dealership_id")
    private DealerShip dealership;

    private Car() {}

    public Car(
            String brand,
            String model,
            String fuelType,
            String engine,
            int seats,
            int price,
            String additionalInfo,
            int stockQuantity
    ) {
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.engine = engine;
        this.seats = seats;
        this.price = price;
        this.additionalInfo = additionalInfo;
        this.stockQuantity = stockQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public DealerShip getDealership() {
        return dealership;
    }

    public void setDealership(DealerShip dealership) {
        this.dealership = dealership;
    }
}