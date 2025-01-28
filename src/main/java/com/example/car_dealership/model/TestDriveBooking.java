package com.example.car_dealership.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "test_drive_bookings")
@Schema(description = "Entity representing a test drive booking.")
public class TestDriveBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique identifier of the test drive booking.", example = "1")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Schema(description = "The customer who booked the test drive.")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "car_id")
    @Schema(description = "The car that will be used in the test drive.")
    private Car car;

    @Schema(description = "The date of the test drive.", example = "2024-02-20")
    private LocalDate date;

    @Schema(description = "The time of the test drive in 24-hour format (HH:mm).", example = "14:30")
    private String time;

    protected TestDriveBooking() {}

    public TestDriveBooking(
            LocalDate date,
            String time
    ) {
        this.date = date;
        this.time = time;
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