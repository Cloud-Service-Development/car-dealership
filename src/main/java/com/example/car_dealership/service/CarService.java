package com.example.car_dealership.service;

import com.example.car_dealership.CarDealershipApplication;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private DealershipRepository dealershipRepository;
    private CarRepository carRepository;

    @Autowired
    public CarService(DealershipRepository dealershipRepository, CarRepository carRepository) {
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public Car addCar(Long dealershipId,Car car) {
        // Εύρεση της αντιπροσωπείας
        DealerShip dealership = dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new IllegalArgumentException("Dealership not found"));

        //Συσχέτιση του αυτοκινήτου με την αντιπροσωπεία
        car.setDealerShip(dealership);

        //Αποθήκευση του αυτοκινήτου στη βάση δεδομένων
        return carRepository.save(car);

    }
}
