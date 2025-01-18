package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.service.TestDriveBookingService;
import com.example.car_dealership.util.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
public class TestDriveBookingController {

    private final TestDriveBookingService testDriveBookingService;

    public TestDriveBookingController(
            TestDriveBookingService testDriveBookingService
    ) {
        this.testDriveBookingService = testDriveBookingService;
    }

    @PostMapping("/customer/dashboard/cars/test-drive")
    public ResponseEntity<String> addTestDriveBooking(
            @Valid @RequestBody CustomerCreateUpdateTestDriveRequest testDriveBooking,
            @RequestParam int customerId,
            @RequestParam int carId,
            BindingResult bindingResult
    ) {
        ValidationUtils.validateId(customerId, "customerId");
        ValidationUtils.validateId(carId, "carId");

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage
            );
        }

        try {
            testDriveBookingService.addTestDriveBooking(
                    testDriveBooking,
                    customerId,
                    carId
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return ResponseEntity.ok().body("The purchase has been made!");
    }
}
