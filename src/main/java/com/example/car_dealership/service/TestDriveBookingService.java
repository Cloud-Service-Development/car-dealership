package com.example.car_dealership.service;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.mapper.TestDriveBookingMapper;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.TestDriveBooking;
import com.example.car_dealership.repository.CarRepository;
import com.example.car_dealership.repository.TestDriveBookingRepository;
import org.springframework.stereotype.Service;

@Service
public class TestDriveBookingService {

    private final TestDriveBookingRepository testDriveBookingRepository;
    private final CarRepository carRepository;
    private final EntityFinderService entityFinderService;

    public TestDriveBookingService(
            TestDriveBookingRepository testDriveBookingRepository,
            CarRepository carRepository,
            EntityFinderService entityFinderService
    ) {
        this.testDriveBookingRepository = testDriveBookingRepository;
        this.carRepository = carRepository;
        this.entityFinderService = entityFinderService;
    }

    public void addTestDriveBooking(
            CustomerCreateUpdateTestDriveRequest testDriveBookingDTO,
            int customerId,
            int carId
    ) {
        Customer customer = entityFinderService.findCustomerById(customerId);
        Car car = entityFinderService.findCarById(carId);

        TestDriveBooking testDriveBooking = TestDriveBookingMapper.toEntity(testDriveBookingDTO);
        testDriveBooking.setCustomer(customer);

        int currentStockQuantity = car.getStockQuantity();
        car.setStockQuantity(currentStockQuantity - 1);
        carRepository.save(car);

        testDriveBooking.setCar(car);

        testDriveBookingRepository.save(testDriveBooking);
    }
}
