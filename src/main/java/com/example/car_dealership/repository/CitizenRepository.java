package com.example.car_dealership.repository;

import com.example.car_dealership.model.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}
