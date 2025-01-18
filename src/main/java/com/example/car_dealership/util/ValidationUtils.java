package com.example.car_dealership.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidationUtils {

    private ValidationUtils() {}

    public static void validateId(
            int id,
            String fieldName
    ) {
        if (id <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Invalid " + fieldName + ". It must be a positive number."
            );
        }
    }
}