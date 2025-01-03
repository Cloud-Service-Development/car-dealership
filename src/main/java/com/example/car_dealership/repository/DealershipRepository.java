package com.example.car_dealership.repository;

import com.example.car_dealership.model.DealerShip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealershipRepository extends JpaRepository<DealerShip,Long> {

}
