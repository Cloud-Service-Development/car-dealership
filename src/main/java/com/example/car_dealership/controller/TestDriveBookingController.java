package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.model.TestDriveBooking;
import com.example.car_dealership.service.TestDriveBookingService;
import com.example.car_dealership.util.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class TestDriveBookingController {

    private final TestDriveBookingService testDriveBookingService;

    public TestDriveBookingController(
            TestDriveBookingService testDriveBookingService
    ) {
        this.testDriveBookingService = testDriveBookingService;
    }

    @Operation(
            summary = "Book a test drive",
            description = "Allows a customer to book a test drive for a specific car."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Booking successful",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "The booking has been made!"))),
            @ApiResponse(responseCode = "400", description = "Invalid request (e.g., missing fields, incorrect IDs)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/customer/{customerId}/dashboard/cars/{carId}/test-drive")
    public ResponseEntity<String> addTestDriveBooking(
            @Valid @RequestBody CustomerCreateUpdateTestDriveRequest testDriveBooking,
            @PathVariable int customerId,
            @PathVariable int carId,
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

        return ResponseEntity.ok().body("The booking has been made!");
    }

    @Operation(summary = "Get test drive bookings for a car", description = "Retrieves the list of booked test drive dates for a specific car.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of bookings",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TestDriveBooking.class))),
            @ApiResponse(responseCode = "400", description = "Invalid car ID or other bad request errors",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/customer/dashboard/cars/{carId}/test-drive/booked-dates")
    public ResponseEntity<List<TestDriveBooking>> getTestDriveBookingsForCar(
            @PathVariable int carId
    ) {
        ValidationUtils.validateId(carId, "carId");

        try {
            List<TestDriveBooking> bookings = testDriveBookingService.getTestDriveBookingsForCar(carId);
            return ResponseEntity.ok().body(bookings);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }
    }
}
