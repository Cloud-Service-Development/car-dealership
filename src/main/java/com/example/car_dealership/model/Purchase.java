package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "purchases")
@Schema(description = "Entity representing a car purchase by a customer.")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the purchase.", example = "1")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Schema(description = "The customer who made the purchase.")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "car_id")
    @Schema(description = "The car that was purchased.")
    private Car car;

    @Schema(description = "The date the purchase was made.", example = "2024-02-15")
    private LocalDate purchaseDate;

    @Schema(description = "The total amount paid for the car.", example = "30000")
    private int amount;

    protected Purchase() {}

    public Purchase(
            LocalDate purchaseDate,
            int amount
    ) {
        this.purchaseDate = purchaseDate;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}