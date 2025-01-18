package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(
            PurchaseService purchaseService
    ) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("/customer/dashboard/cars/purchase")
    public ResponseEntity<String> addPurchase(
            @Valid @RequestBody CustomerCreateUpdatePurchaseRequest purchase,
            @RequestParam int customerId,
            @RequestParam int carId,
            BindingResult bindingResult
    ) {
        if (customerId <= 0) {
            return ResponseEntity.badRequest().body("Invalid customerId. It must be a positive number.");
        }

        if (carId <= 0) {
            return ResponseEntity.badRequest().body("Invalid carId. It must be a positive number.");
        }

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }

        try {
            purchaseService.addPurchase(
                    purchase,
                    customerId,
                    carId
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok().body("The purchase has been made!");
    }
}
