package com.example.car_dealership.service;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.mapper.CarMapper;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.DealershipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final EntityFinderService entityFinderService;

    public CarService(
            CarRepository carRepository,
            EntityFinderService entityFinderService
    ) {
        this.carRepository = carRepository;
        this.entityFinderService = entityFinderService;
    }

    public List<Car> getAllCars() {
        return carRepository.findAllByOrderByIdDesc();
    }

    public void addCar(
            int dealershipId,
            DealershipCreateUpdateCarRequest carDTO
    ) {
        DealerShip dealership = entityFinderService.findDealershipById(dealershipId);
        Car car = CarMapper.toEntity(carDTO);
        car.setDealership(dealership);
        carRepository.save(car);
    }

    public void editCar(
            int carId,
            DealershipCreateUpdateCarRequest carDTO
    ) {
        Car existingCar = entityFinderService.findCarById(carId);

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
