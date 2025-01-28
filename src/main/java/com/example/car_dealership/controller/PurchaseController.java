package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerCreateUpdatePurchaseRequest;
import com.example.car_dealership.service.PurchaseService;
import com.example.car_dealership.util.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@SecurityRequirement(name = "bearerAuth")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(
            PurchaseService purchaseService
    ) {
        this.purchaseService = purchaseService;
    }

    @Operation(
            summary = "Purchase a car",
            description = "Allows a customer to purchase a specific car."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Purchase successful",
                    content = @Content(mediaType = "text/plain", schema = @Schema(example = "The purchase has been made!"))),
            @ApiResponse(responseCode = "400", description = "Invalid request (e.g., missing fields, incorrect IDs, validation errors)",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Authentication required",
                    content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/customer/{customerId}/dashboard/cars/{carId}/purchase")
    public ResponseEntity<String> addPurchase(
            @Valid @RequestBody CustomerCreateUpdatePurchaseRequest purchase,
            @PathVariable int customerId,
            @PathVariable int carId,
            BindingResult bindingResult
    ) {
        ValidationUtils.validateId(customerId, "customerId");
        ValidationUtils.validateId(carId, "carId");

        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    errorMessage
            );
        }

        try {
            purchaseService.addPurchase(
                    purchase,
                    customerId,
                    carId
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        }

        return ResponseEntity.ok().body("The purchase has been made!");
    }
}
