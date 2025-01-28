package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;

/**
 * Utility class for mapping DealershipCreateUpdateCarRequest DTO to Car entity.
 */
public class CarMapper {

    /**
     * Converts a DealershipCreateUpdateCarRequest DTO to a Car entity.
     *
     * @param carDTO The DTO containing car details provided by the dealership.
     * @return A Car entity populated with the provided details.
     */
    public static Car toEntity(
            DealershipCreateUpdateCarRequest carDTO
    ) {
        return new Car(
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getFuelType(),
                carDTO.getEngine(),
                carDTO.getSeats(),
                carDTO.getPrice(),
                carDTO.getAdditionalInfo(),
                carDTO.getStockQuantity()
        );
    }
}
