package com.example.car_dealership.controller;

import com.example.car_dealership.model.TestDriveBooking;
import com.example.car_dealership.service.TestDriveBookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDriveBookingController {

    private final TestDriveBookingService testDriveBookingService;

    public TestDriveBookingController(
            TestDriveBookingService testDriveBookingService
    ) {
        this.testDriveBookingService = testDriveBookingService;
    }

    @PostMapping("/customer/dashboard/cars/test-drive")
    public TestDriveBooking addTestDriveBooking(
            @RequestBody TestDriveBooking testDriveBooking,
            @RequestParam int customerId,
            @RequestParam int carId
    ) {
        return this.testDriveBookingService.addTestDriveBooking(
                testDriveBooking,
                customerId,
                carId
        );
    }
}
