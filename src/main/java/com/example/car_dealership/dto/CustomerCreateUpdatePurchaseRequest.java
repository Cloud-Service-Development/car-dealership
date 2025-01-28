package com.example.car_dealership.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Request object for creating or updating a customer purchase.")
public class CustomerCreateUpdatePurchaseRequest {

    @Schema(
            description = "The date of the purchase. It must not be in the future.",
            example = "2023-12-31"
    )
    @NotNull(message = "Purchase date is required.")
    @PastOrPresent(message = "Purchase date cannot be in the future.")
    private LocalDate purchaseDate;

    @Schema(
            description = "The amount of the purchase. It must be at least 1.",
            example = "1000"
    )
    @Min(value = 1, message = "Amount must be at least 1.")
    @NotNull(message = "Amount is required.")
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