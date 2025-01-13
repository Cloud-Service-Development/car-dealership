package com.example.car_dealership.repository;

import com.example.car_dealership.model.TestDriveBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDriveBookingRepository extends JpaRepository<TestDriveBooking, Integer> {
    List<TestDriveBooking> findByCustomerIdOrderByIdDesc(int customerId);
}
