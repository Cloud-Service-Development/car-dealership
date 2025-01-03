package com.example.car_dealership.service;

import com.example.car_dealership.model.Citizen;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.User;
import com.example.car_dealership.repository.CitizenRepository;
import com.example.car_dealership.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.car_dealership.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

    public Citizen registerCitizen(String username, String password, String email, String name) {
        Citizen citizen = new Citizen();
        citizen.setUsername(username);
        citizen.setPassword(password);
        citizen.setEmail(email);
        citizen.setName(name);

        return citizenRepository.save(citizen);
    }

    public DealerShip registerDealership(String username, String password, String email, String name, String location, String contactInfo) {
        DealerShip dealership = new DealerShip();
        dealership.setUsername(username);
        dealership.setPassword(password);
        dealership.setEmail(email);
        dealership.setName(name);
        dealership.setLocation(location);
        dealership.setContactInfo(contactInfo);

        return dealershipRepository.save(dealership);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
