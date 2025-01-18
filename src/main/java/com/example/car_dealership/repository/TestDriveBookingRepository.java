package com.example.car_dealership.repository;

import com.example.car_dealership.model.TestDriveBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveBookingRepository extends JpaRepository<TestDriveBooking, Integer> {
    List<TestDriveBooking> findByCustomerIdOrderByIdDesc(int customerId);
    List<TestDriveBooking> findByCarId(int carId);
}
