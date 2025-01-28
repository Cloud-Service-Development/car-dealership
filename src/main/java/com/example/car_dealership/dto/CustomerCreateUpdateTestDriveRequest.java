package com.example.car_dealership.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Request object for creating or updating a customer test drive.")
public class CustomerCreateUpdateTestDriveRequest {

    @Schema(
            description = "The date of the test drive. It cannot be in the past.",
            example = "2024-02-15"
    )
    @NotNull(message = "Date is required.")
    @FutureOrPresent(message = "The test drive date cannot be in the past.")
    private LocalDate date;

    @Schema(
            description = "The time of the test drive in 24-hour format (HH:mm).",
            example = "14:30"
    )
    @NotNull(message = "Time is required.")
    @Pattern(
            regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
            message = "Time must be in the format HH:mm (24-hour format)."
    )
    private String time;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}