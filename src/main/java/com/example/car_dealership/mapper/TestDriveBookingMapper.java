package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.model.TestDriveBooking;

public class TestDriveBookingMapper {

    public static TestDriveBooking toEntity(
            CustomerCreateUpdateTestDriveRequest testDriveDTO
    ) {
        return new TestDriveBooking(
                testDriveDTO.getDate(),
                testDriveDTO.getTime()
        );
    }
}
