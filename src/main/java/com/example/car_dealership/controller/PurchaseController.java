package com.example.car_dealership.controller;

import com.example.car_dealership.model.Purchase;
import com.example.car_dealership.service.PurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(
            PurchaseService purchaseService
    ) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/customer/dashboard/cars/purchase")
    public Purchase addPurchase(
            @RequestBody Purchase purchase,
            @RequestParam int customerId,
            @RequestParam int carId
    ) {
        return this.purchaseService.addPurchase(
                purchase,
                customerId,
                carId
        );
    }
}
