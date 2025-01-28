package com.example.car_dealership.mapper;

import com.example.car_dealership.dto.CustomerCreateUpdateTestDriveRequest;
import com.example.car_dealership.model.TestDriveBooking;

/**
 * Utility class for mapping CustomerCreateUpdateTestDriveRequest DTO to TestDriveBooking entity.
 */
public class TestDriveBookingMapper {

    /**
     * Converts a CustomerCreateUpdateTestDriveRequest DTO to a TestDriveBooking entity.
     *
     * @param testDriveDTO The DTO containing test drive details provided by the customer.
     * @return A TestDriveBooking entity populated with the provided details.
     */
    public static TestDriveBooking toEntity(
            CustomerCreateUpdateTestDriveRequest testDriveDTO
    ) {
        return new TestDriveBooking(
                testDriveDTO.getDate(),
                testDriveDTO.getTime()
        );
    }
}
