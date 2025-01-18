package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.model.Purchase;

public class PurchaseMapper {

    public static Purchase toEntity(
            CustomerCreateUpdatePurchaseRequest purchaseDTO
    ) {
        return new Purchase(
                purchaseDTO.getPurchaseDate(),
                purchaseDTO.getAmount()
        );
    }
}
