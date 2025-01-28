package com.example.car_dealership.controller;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.service.CarService;
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
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            summary = "Get all cars",
            description = "Retrieves a list of all available cars."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of cars",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/customer/dashboard/cars/all")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Get cars for a dealership",
            description = "Retrieves a list of cars available for the specified dealership."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of cars",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "400", description = "Invalid dealership ID or other bad request errors",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/dealership/{dealershipId}/dashboard/cars")
    public ResponseEntity<List<Car>> getDealershipCars(
            @PathVariable int dealershipId
    ) {
        ValidationUtils.validateId(dealershipId, "dealershipId");
        List<Car> cars = carService.getDealershipCars(dealershipId);
        return ResponseEntity.ok(cars);
    }

    @Operation(
            summary = "Add a new car to a dealership",
            description = "Allows a dealership to add a new car by providing the dealership ID and car details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Car successfully added",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "The car has been created!"))),
            @ApiResponse(responseCode = "400", description = "Invalid request (e.g., missing fields or validation errors)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/dealership/{dealershipId}/dashboard/add-car")
    public ResponseEntity<String> addCar(
            @PathVariable int dealershipId,
            @Valid @RequestBody DealershipCreateUpdateCarRequest carDTO,
            BindingResult bindingResult
    ) {
        ValidationUtils.validateId(dealershipId, "dealershipId");

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
            carService.addCar(dealershipId, carDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return ResponseEntity.ok().body("The car has been created!");
    }

    @Operation(
            summary = "Edit an existing car",
            description = "Allows a dealership to update the details of an existing car by providing the car ID and the updated car details."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Car successfully updated",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "The car has been updated!"))),
            @ApiResponse(responseCode = "400", description = "Invalid request (e.g., missing fields or validation errors)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/dealership/dashboard/edit-car/{carId}")
    public ResponseEntity<String> editCar(
            @PathVariable int carId,
            @Valid @RequestBody DealershipCreateUpdateCarRequest carDTO,
            BindingResult bindingResult
    ) {
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
            carService.editCar(carId, carDTO);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return ResponseEntity.ok().body("The car has been updated!");
    }
}
