package com.example.car_dealership.service;

import com.example.car_dealership.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_dealership.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {

        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}