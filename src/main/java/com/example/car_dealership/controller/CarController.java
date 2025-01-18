package com.example.car_dealership.controller;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.service.CarService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/customer/dashboard/cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/dealership/dashboard/cars")
    public List<Car> getDealershipCars(@RequestParam int dealershipId) {
        return carService.getDealershipCars(dealershipId);
    }

    @PostMapping("/dealership/dashboard/add-car")
    public ResponseEntity<String> addCar(
            @RequestParam int dealershipId,
            @Valid @RequestBody DealershipCreateUpdateCarRequest carDTO,
            BindingResult bindingResult
    ) {
        if (dealershipId <= 0) {
            return ResponseEntity.badRequest().body("Invalid dealershipId. It must be a positive number.");
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            carService.addCar(dealershipId, carDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("The car has been created!");
    }

    @PostMapping("/dealership/dashboard/edit-car")
    public ResponseEntity<String> editCar(
            @RequestParam int carId,
            @Valid @RequestBody DealershipCreateUpdateCarRequest carDTO,
            BindingResult bindingResult
    ) {
        if (carId <= 0) {
            return ResponseEntity.badRequest().body("Invalid carId. It must be a positive number.");
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            carService.editCar(carId, carDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("The car has been updated!");
    }
}
