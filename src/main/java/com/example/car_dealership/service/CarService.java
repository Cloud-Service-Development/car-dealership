package com.example.car_dealership.service;

import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.DealershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final DealershipRepository dealershipRepository;

    public CarService(CarRepository carRepository, DealershipRepository dealershipRepository) {
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAllByOrderByIdDesc();
    }

    public Car addCar(int dealershipId, Car car) {
        DealerShip dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Dealership not found"));

        car.setDealership(dealership);
        return carRepository.save(car);
    }

    public Car editCar(int carId, Car car) {
        Optional<Car> carOptional = carRepository.findById(carId);

        if (carOptional.isEmpty()) {
            throw new EntityNotFoundException("Car with id " + carId + " not found");
        }

        Car existingCar = carOptional.get();

        existingCar.setBrand(car.getBrand());
        existingCar.setModel(car.getModel());
        existingCar.setFuelType(car.getFuelType());
        existingCar.setEngine(car.getEngine());
        existingCar.setSeats(car.getSeats());
        existingCar.setPrice(car.getPrice());
        existingCar.setAdditionalInfo(car.getAdditionalInfo());
        existingCar.setStockQuantity(car.getStockQuantity());

        carRepository.save(existingCar);

        return existingCar;
    }

    public List<Car> getDealershipCars(int dealershipId) {
        return carRepository.findByDealershipIdOrderByIdDesc(dealershipId);
    }
}
