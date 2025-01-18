package com.example.car_dealership.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CustomerCreateUpdateTestDriveRequest {

    @NotNull(message = "Date is required.")
    @FutureOrPresent(message = "The test drive date cannot be in the past.")
    private LocalDate date;

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
