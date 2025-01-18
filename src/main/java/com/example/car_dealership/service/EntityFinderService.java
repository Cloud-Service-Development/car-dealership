package com.example.car_dealership.service;

import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.CustomerRepository;
import com.example.car_dealership.repository.DealershipRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EntityFinderService {

    private final CarRepository carRepository;
    private final DealershipRepository dealershipRepository;
    private final CustomerRepository customerRepository;

    public EntityFinderService(
            CarRepository carRepository,
            DealershipRepository dealershipRepository,
            CustomerRepository customerRepository
    ) {
        this.carRepository = carRepository;
        this.dealershipRepository = dealershipRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Fetch a dealership by ID or throw an exception if not found.
     *
     * @param dealershipId The dealership ID
     * @return DealerShip entity
     */
    public DealerShip findDealershipById(int dealershipId) {
        return dealershipRepository.findById(dealershipId)
                .orElseThrow(() -> new EntityNotFoundException("Dealership not found with ID: " + dealershipId));
    }

    /**
     * Fetch a car by ID or throw an exception if not found.
     *
     * @param carId The car ID
     * @return Car entity
     */
    public Car findCarById(int carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found with ID: " + carId));
    }

    /**
     * Fetch a customer by ID or throw an exception if not found.
     *
     * @param customerId The customer ID
     * @return Customer entity
     */
    public Customer findCustomerById(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));
    }
}