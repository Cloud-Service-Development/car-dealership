package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerRegisterRequest;
import com.example.car_dealership.dto.DealershipRegisterRequest;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.InternalUser;
import com.example.car_dealership.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder encodePassword;

    @PostMapping(value = "/register/dealership", consumes = "application/json")
    public ResponseEntity<String> registerDealership(
            @Valid @RequestBody DealershipRegisterRequest dealershipRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }

        dealershipRequest.setPassword(encodePassword.encode(dealershipRequest.getPassword()));
        InternalUser user = new InternalUser(
                dealershipRequest.getUsername(),
                dealershipRequest.getEmail(),
                dealershipRequest.getPassword(),
                "dealership"
        );
        DealerShip dealership = new DealerShip(dealershipRequest.getName(), dealershipRequest.getTaxNumber(), user);

        registrationService.registerDealership(dealership);
        return ResponseEntity.ok().body("Dealership registered successfully!");
    }

    @PostMapping(value = "/register/customer", consumes = "application/json")
    public ResponseEntity<String> registerCustomer(
            @Valid @RequestBody CustomerRegisterRequest customerRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMessage);
        }

        customerRequest.setPassword(encodePassword.encode(customerRequest.getPassword()));
        InternalUser user = new InternalUser(
                customerRequest.getUsername(),
                customerRequest.getEmail(),
                customerRequest.getPassword(),
                "customer"
        );
        Customer customer = new Customer(
                customerRequest.getFirstName(),
                customerRequest.getLastName(),
                customerRequest.getTaxNumber(),
                user
        );
        registrationService.registerCustomer(customer);
        return ResponseEntity.ok().body("Customer registered successfully!");
    }
}
