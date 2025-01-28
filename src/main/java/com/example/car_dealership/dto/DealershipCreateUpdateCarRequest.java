package com.example.car_dealership.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Request object for creating or updating a car in a dealership.")
public class DealershipCreateUpdateCarRequest {

    @Schema(
            description = "The brand of the car.",
            example = "Toyota"
    )
    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    private String brand;

    @Schema(
            description = "The model of the car.",
            example = "Corolla"
    )
    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @Schema(
            description = "The type of fuel the car uses. Must be one of: Gasoline, Diesel, Electric, or Hybrid.",
            example = "Gasoline"
    )
    @NotBlank(message = "Fuel type is required")
    @Pattern(
            regexp = "^(Gasoline|Diesel|Electric|Hybrid)$",
            message = "Fuel type must be one of: Gasoline, Diesel, Electric, or Hybrid"
    )
    private String fuelType;

    @Schema(
            description = "A description of the car's engine.",
            example = "1.8L 4-Cylinder"
    )
    @NotBlank(message = "Engine is required")
    @Size(max = 100, message = "Engine description cannot exceed 100 characters")
    private String engine;

    @Schema(
            description = "The number of seats in the car. Must be between 2 and 9.",
            example = "5"
    )
    @Min(value = 2, message = "Seats must be at least 2")
    @Max(value = 9, message = "Seats cannot exceed 9")
    private int seats;

    @Schema(
            description = "The price of the car. Must be a positive value.",
            example = "25000"
    )
    @Min(value = 0, message = "Price must be a positive value")
    private int price;

    @Schema(
            description = "Additional information about the car.",
            example = "Includes advanced safety features and a premium sound system."
    )
    @NotBlank(message = "Additional info field is required")
    @Size(max = 500, message = "Additional information cannot exceed 500 characters")
    private String additionalInfo;

    @Schema(
            description = "The number of cars available in stock. Must be a positive value.",
            example = "10"
    )
    @Min(value = 0, message = "Stock quantity must be a positive value")
    private int stockQuantity;

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
}