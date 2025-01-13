package com.example.car_dealership.service;

import com.example.car_dealership.model.*;
import com.example.car_dealership.repository.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PageService {

    private final UserRepository userRepository;
    private final DealershipRepository dealershipRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final TestDriveBookingRepository testDriveBookingRepository;
    private final PurchaseRepository purchaseRepository;

    public PageService(
            UserRepository userRepository,
            DealershipRepository dealershipRepository,
            CarRepository carRepository,
            CustomerRepository customerRepository,
            TestDriveBookingRepository testDriveBookingRepository,
            PurchaseRepository purchaseRepository
    ) {
        this.userRepository = userRepository;
        this.dealershipRepository = dealershipRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.testDriveBookingRepository = testDriveBookingRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public DealerShip getDealershipByUserId(int userId) {
        return dealershipRepository.findByUserId(userId);
    }

    public Customer getCustomerByUserId(int userId) {
        return customerRepository.findByUserId(userId);
    }

    public List<Car> getDealershipCars(int dealershipId) {
        return carRepository.findByDealershipIdOrderByIdDesc(dealershipId);
    }

    public Optional<Car> getCarById(int carId) {
        return carRepository.findById(carId);
    }

    public List<Car> getAllCarsForCustomer() {
        return carRepository.findAllByOrderByIdDesc();
    }

    public List<TestDriveBooking> getAllTestDriveBookingsForCustomer(int customerId) {
        return testDriveBookingRepository.findByCustomerIdOrderByIdDesc(customerId);
    }

    public List<Purchase> getAllPurchasesForCustomer(int customerId) {
        return purchaseRepository.findByCustomerIdOrderByIdDesc(customerId);
    }

    public Optional<InternalUser> getCurrentUser(Principal principal) {
        String username = principal.getName();
        Optional<InternalUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

    public String getHomeTemplate(Principal principal) {
        Optional<InternalUser> user = getCurrentUser(principal);

        if (user.isPresent() && Objects.equals(user.get().getRole(), "customer")) {
            return "redirect:/customer/dashboard";
        }

        if (user.isPresent() && Objects.equals(user.get().getRole(), "dealership")) {
            return "redirect:/dealership/dashboard";
        }

        return "";
    }
}
