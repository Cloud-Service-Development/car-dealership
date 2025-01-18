package com.example.car_dealership.service;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.mapper.TestDriveBookingMapper;
import com.example.car_dealership.model.Car;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.TestDriveBooking;
import com.example.car_dealership.repository.TestDriveBookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestDriveBookingService {

    private final TestDriveBookingRepository testDriveBookingRepository;
    private final EntityFinderService entityFinderService;

    public TestDriveBookingService(
            TestDriveBookingRepository testDriveBookingRepository,
            EntityFinderService entityFinderService
    ) {
        this.testDriveBookingRepository = testDriveBookingRepository;
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
        testDriveBooking.setCar(car);
        testDriveBookingRepository.save(testDriveBooking);
    }

    public List<TestDriveBooking> getTestDriveBookingsForCar(int carId) {
        return testDriveBookingRepository.findByCarId(carId);
    }
}
