package com.example.car_dealership.repository;

import com.example.car_dealership.model.DealerShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealershipRepository extends JpaRepository<DealerShip, Integer> {
    DealerShip findByUserId(int userId);
}
