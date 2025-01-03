package com.example.car_dealership.controller;

import com.example.car_dealership.model.User;
import com.example.car_dealership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/start")
    public String testEndpoint(){

        return "Hello World";
    }
    // Παράδειγμα: GET /users
    @GetMapping
    public List<User> getAllUsers() {
        // Επιστροφή λίστας χρηστών
        return userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}