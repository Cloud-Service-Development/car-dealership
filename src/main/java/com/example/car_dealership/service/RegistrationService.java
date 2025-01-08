package com.example.car_dealership.service;

import com.example.car_dealership.model.Customer;
import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.InternalUser;
import com.example.car_dealership.repository.CustomerRepository;
import com.example.car_dealership.repository.DealershipRepository;
import com.example.car_dealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

@Service
public class RegistrationService {

    private final DealershipRepository dealershipRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RegistrationService(
            DealershipRepository dealershipRepository,
            UserRepository userRepository,
            CustomerRepository customerRepository
    ) {
        this.dealershipRepository = dealershipRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public void registerDealership(DealerShip dealership) {
        InternalUser savedUser = userRepository.save(dealership.getUser());
        dealership.setUser(savedUser);
        dealershipRepository.save(dealership);
    }

    @Transactional
    public void registerCustomer(Customer customer) {
        InternalUser savedUser = userRepository.save(customer.getUser());
        customer.setUser(savedUser);
        customerRepository.save(customer);
    }
}
