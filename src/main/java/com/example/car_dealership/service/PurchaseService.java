package com.example.car_dealership.service;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.mapper.PurchaseMapper;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.Purchase;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CarRepository carRepository;
    private final EntityFinderService entityFinderService;

    public PurchaseService(
            PurchaseRepository purchaseRepository,
            CarRepository carRepository,
            EntityFinderService entityFinderService
    ) {
        this.purchaseRepository = purchaseRepository;
        this.carRepository = carRepository;
        this.entityFinderService = entityFinderService;
    }

    public void addPurchase(
            CustomerCreateUpdatePurchaseRequest purchaseDTO,
            int customerId,
            int carId
    ) {
        Customer customer = entityFinderService.findCustomerById(customerId);
        Car car = entityFinderService.findCarById(carId);

        Purchase purchase = PurchaseMapper.toEntity(purchaseDTO);
        purchase.setCustomer(customer);

        int currentStockQuantity = car.getStockQuantity();
        car.setStockQuantity(currentStockQuantity - 1);
        carRepository.save(car);

        purchase.setCar(car);

        purchaseRepository.save(purchase);
    }
}
