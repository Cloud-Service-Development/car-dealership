package com.example.car_dealership.controller;

import com.example.car_dealership.model.Citizen;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.User;
import com.example.car_dealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("test")
    public String test() {
        return "Hello World";
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/register/citizen")
    public ResponseEntity<Citizen> registerCitizen(@RequestBody Citizen citizen) {
        Citizen registeredCitizen = userService.registerCitizen(
                    citizen.getUsername(),
                    citizen.getPassword(),
                    citizen.getEmail(),
                    citizen.getName());
        return ResponseEntity.ok(registeredCitizen);
        }
    @PostMapping("/register/dealership")
    public ResponseEntity<DealerShip> registerDealership(@RequestBody DealerShip dealership) {
        DealerShip registeredDealership = userService.registerDealership(
                    dealership.getUsername(),
                    dealership.getPassword(),
                    dealership.getEmail(),
                    dealership.getName(),
                    dealership.getLocation(),
                    dealership.getContactInfo());
        return ResponseEntity.ok(registeredDealership);
    }
}
