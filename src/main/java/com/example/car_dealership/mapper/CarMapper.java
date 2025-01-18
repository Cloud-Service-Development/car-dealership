package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.DealershipCreateUpdateCarRequest;
import com.example.car_dealership.model.Car;

public class CarMapper {

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
