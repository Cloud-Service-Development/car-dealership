package com.example.car_dealership.controller;

import com.example.car_dealership.model.Car;
import com.example.car_dealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CarController {

    @Autowired
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/dashboard/all-cars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/dealership/dashboard/cars")
    public List<Car> getDealershipCars(@RequestParam int dealershipId) {
        return carService.getDealershipCars(dealershipId);
    }

    @PostMapping("/dealership/dashboard/add-car")
    public ResponseEntity<Car> addCar(@RequestParam int dealershipId, @RequestBody Car car) {
        Car savedCar = carService.addCar(dealershipId, car);
        return ResponseEntity.ok(savedCar);
    }
}
