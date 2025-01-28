package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
@Schema(description = "Entity representing a car in the dealership.")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the car.", example = "1")
    private int id;

    @Schema(description = "The brand of the car.", example = "Toyota")
    private String brand;

    @Schema(description = "The model of the car.", example = "Corolla")
    private String model;

    @Schema(description = "The type of fuel the car uses.", example = "Gasoline")
    private String fuelType;

    @Schema(description = "The engine specifications of the car.", example = "1.8L 4-Cylinder")
    private String engine;

    @Schema(description = "The number of seats in the car.", example = "5")
    private int seats;

    @Schema(description = "The price of the car in dollars.", example = "25000")
    private int price;

    @Schema(description = "Additional information about the car.",
            example = "Includes advanced safety features and a premium sound system.")
    private String additionalInfo;

    @Schema(description = "The stock quantity of the car in the dealership.", example = "10")
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "dealership_id")
    @Schema(description = "The dealership to which the car belongs.")
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