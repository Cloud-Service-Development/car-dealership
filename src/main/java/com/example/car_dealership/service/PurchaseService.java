package com.example.car_dealership.service;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.mapper.PurchaseMapper;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.Purchase;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.CustomerRepository;
import com.example.car_dealership.repository.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public PurchaseService(
            PurchaseRepository purchaseRepository,
            CustomerRepository customerRepository,
            CarRepository carRepository
    ) {
        this.purchaseRepository = purchaseRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public void addPurchase(
            CustomerCreateUpdatePurchaseRequest purchaseDTO,
            int customerId,
            int carId
    ) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        Purchase purchase = PurchaseMapper.toEntity(purchaseDTO);
        purchase.setCustomer(customer);

        int currentStockQuantity = car.getStockQuantity();
        car.setStockQuantity(currentStockQuantity - 1);
        carRepository.save(car);

        purchase.setCar(car);

        purchaseRepository.save(purchase);
    }
}
