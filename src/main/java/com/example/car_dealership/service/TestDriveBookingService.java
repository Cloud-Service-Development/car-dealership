package com.example.car_dealership.service;

import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.TestDriveBooking;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.CustomerRepository;
import com.example.car_dealership.repository.TestDriveBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class TestDriveBookingService {

    private final TestDriveBookingRepository testDriveBookingRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    public TestDriveBookingService(
            TestDriveBookingRepository testDriveBookingRepository,
            CustomerRepository customerRepository,
            CarRepository carRepository
    ) {
        this.testDriveBookingRepository = testDriveBookingRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public TestDriveBooking addTestDriveBooking(
            TestDriveBooking testDriveBooking,
            int customerId,
            int carId
    ) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        testDriveBooking.setCustomer(customer);

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        int currentStockQuantity = car.getStockQuantity();
        car.setStockQuantity(currentStockQuantity - 1);
        carRepository.save(car);

        testDriveBooking.setCar(car);

        return testDriveBookingRepository.save(testDriveBooking);
    }
}
