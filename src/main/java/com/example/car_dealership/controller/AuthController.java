package com.example.car_dealership.controller;

import com.example.car_dealership.dto.CustomerRegisterRequest;
import com.example.car_dealership.dto.DealershipRegisterRequest;
import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.InternalUser;
import com.example.car_dealership.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PasswordEncoder encodePassword;

    @PostMapping(value = "/register/dealership", consumes = "application/json")
    public String registerDealership(@RequestBody DealershipRegisterRequest dealershipRequest) {
        dealershipRequest.setPassword(encodePassword.encode(dealershipRequest.getPassword()));
        InternalUser user = new InternalUser(
                dealershipRequest.getUsername(),
                dealershipRequest.getEmail(),
                dealershipRequest.getPassword(),
                "dealership"
        );
        DealerShip dealership = new DealerShip(dealershipRequest.getName(), dealershipRequest.getTaxNumber(), user);

        registrationService.registerDealership(dealership);
        return "Dealership registered successfully!";
    }

    @PostMapping(value = "/register/customer", consumes = "application/json")
    public String registerCustomer(@RequestBody CustomerRegisterRequest customerRequest) {
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
        return "Customer registered successfully!";
    }
}
