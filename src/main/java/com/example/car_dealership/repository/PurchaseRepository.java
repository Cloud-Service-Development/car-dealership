package com.example.car_dealership.repository;

import com.example.car_dealership.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByCustomerIdOrderByIdDesc(int purchaseId);
}
