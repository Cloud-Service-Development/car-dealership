package com.example.car_dealership.controller;

import com.example.car_dealership.model.Car;
import com.example.car_dealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }
    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestParam Long dealershipId,@RequestBody Car car) {
        Car savedCar = carService.addCar(dealershipId, car);
        return ResponseEntity.ok(savedCar);
   }

}
