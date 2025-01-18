package com.example.car_dealership.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CustomerCreateUpdatePurchaseRequest {

    @NotNull(message = "Purchase date is required.")
    @PastOrPresent(message = "Purchase date cannot be in the future.")
    private LocalDate purchaseDate;

    @Min(value = 1, message = "Amount must be at least 1.")
    private int amount;

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
