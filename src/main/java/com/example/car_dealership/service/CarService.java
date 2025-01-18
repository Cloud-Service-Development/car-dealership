package com.example.car_dealership.service;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.mapper.CarMapper;
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

    public void addCar(
            int dealershipId,
            DealershipCreateUpdateCarRequest carDTO
    ) {
        DealerShip dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new EntityNotFoundException("Dealership not found"));

        Car car = CarMapper.toEntity(carDTO);
        car.setDealership(dealership);
        carRepository.save(car);
    }

    public void editCar(
            int carId,
            DealershipCreateUpdateCarRequest carDTO
    ) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car with id " + carId + " not found"));

        existingCar.setBrand(carDTO.getBrand());
        existingCar.setModel(carDTO.getModel());
        existingCar.setFuelType(carDTO.getFuelType());
        existingCar.setEngine(carDTO.getEngine());
        existingCar.setSeats(carDTO.getSeats());
        existingCar.setPrice(carDTO.getPrice());
        existingCar.setAdditionalInfo(carDTO.getAdditionalInfo());
        existingCar.setStockQuantity(carDTO.getStockQuantity());

        carRepository.save(existingCar);
    }

    public List<Car> getDealershipCars(int dealershipId) {
        return carRepository.findByDealershipIdOrderByIdDesc(dealershipId);
    }
}
