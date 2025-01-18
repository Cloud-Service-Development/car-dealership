package com.example.car_dealership.controller;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.service.CarService;
import com.example.car_dealership.util.ValidationUtils;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/customer/dashboard/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/dealership/dashboard/cars")
    public ResponseEntity<List<Car>> getDealershipCars(
            @RequestParam int dealershipId
    ) {
        ValidationUtils.validateId(dealershipId, "dealershipId");
        List<Car> cars = carService.getDealershipCars(dealershipId);
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/dealership/dashboard/add-car")
    public ResponseEntity<String> addCar(
            @RequestParam int dealershipId,
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

    @PostMapping("/dealership/dashboard/edit-car")
    public ResponseEntity<String> editCar(
            @RequestParam int carId,
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
