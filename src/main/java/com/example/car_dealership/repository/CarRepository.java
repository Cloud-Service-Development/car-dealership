package com.example.car_dealership.repository;

import com.example.car_dealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByDealershipIdOrderByIdDesc(int dealershipId);
    List<Car> findAllByOrderByIdDesc();
}