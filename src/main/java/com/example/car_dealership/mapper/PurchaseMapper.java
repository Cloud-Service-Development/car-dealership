package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.model.Purchase;

/**
 * Utility class for mapping CustomerCreateUpdatePurchaseRequest DTO to Purchase entity.
 */
public class PurchaseMapper {

    /**
     * Converts a CustomerCreateUpdatePurchaseRequest DTO to a Purchase entity.
     *
     * @param purchaseDTO The DTO containing purchase details provided by the customer.
     * @return A Purchase entity populated with the provided details.
     */
    public static Purchase toEntity(
            CustomerCreateUpdatePurchaseRequest purchaseDTO
    ) {
        return new Purchase(
                purchaseDTO.getPurchaseDate(),
                purchaseDTO.getAmount()
        );
    }
}
