package com.example.car_dealership.controller;

import com.example.car_dealership.model.*;
import com.example.car_dealership.service.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class PageController {

    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @ModelAttribute
    public void addCommonAttributes(Model model, Principal principal) {
        if (principal != null) {
            Optional<InternalUser> user = pageService.getCurrentUser(principal);


            model.addAttribute("currentUsername", principal.getName());

            user.ifPresent(internalUser -> model.addAttribute("role", internalUser.getRole()));

            if (user.isPresent() && "dealership".equals(user.get().getRole())) {
                DealerShip dealership = pageService.getDealershipByUserId(user.get().getId());
                List<Car> cars = pageService.getDealershipCars(dealership.getId());
                model.addAttribute("dealershipName", dealership.getName());
                model.addAttribute("dealershipId", dealership.getId());
                model.addAttribute("dealershipCars", cars);
            }

            if (user.isPresent() && "customer".equals((user.get().getRole()))) {
                Customer customer = pageService.getCustomerByUserId(user.get().getId());
                List<Car> cars = pageService.getAllCarsForCustomer();
                List<TestDriveBooking> testDriveBookings = pageService.getAllTestDriveBookingsForCustomer(customer.getId());
                List<Purchase> purchases = pageService.getAllPurchasesForCustomer(customer.getId());
                model.addAttribute("customerId", customer.getId());
                model.addAttribute("cars", cars);
                model.addAttribute("testDriveBookings", testDriveBookings);
                model.addAttribute("purchases", purchases);
            }
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register/customer")
    public String registerCustomer() {
        return "register-customer";
    }

    @GetMapping("/register/dealership")
    public String registerDealership() {
        return "register-dealership";
    }

    @GetMapping({"/", ""})
    public String home(Principal principal) {
        return pageService.getHomeTemplate(principal);
    }

    @GetMapping("/dealership/dashboard")
    public String dealershipDashboard() {
        return "dashboard-dealership";
    }

    @GetMapping("/dealership/dashboard/add-car")
    public String addCar() {
        return "add-a-car";
    }

    @GetMapping("/dealership/dashboard/cars")
    public String cars() {
        return "cars-list-dealership";
    }

    @GetMapping("/dealership/dashboard/edit-car")
    public String editCar(
            @RequestParam int carId,
            Model model
    ) {
        Optional<Car> car = pageService.getCarById(carId);
        model.addAttribute("car", car.orElse(null));
        return "add-a-car";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard() {
        return "dashboard-customer";
    }

    @GetMapping("/customer/dashboard/cars")
    public String customerCars() {
        return "cars-list-customer";
    }

    @GetMapping("/customer/dashboard/cars/test-drive")
    public String testDrive(@RequestParam int carId, Model model) {
        Optional<Car> car = pageService.getCarById(carId);
        model.addAttribute("car", car.orElse(null));
        return "test-drive-customer";
    }

    @GetMapping("/customer/dashboard/test-drive-bookings")
    public String testDriveBookings() {
        return "test-drive-bookings-list-customer";
    }

    @GetMapping("/customer/dashboard/cars/purchase")
    public String purchaseCar(
            @RequestParam int carId,
            Model model
    ) {
        Optional<Car> car = pageService.getCarById(carId);
        model.addAttribute("car", car.orElse(null));
        return "purchase-car-customer";
    }

    @GetMapping("/customer/dashboard/purchases")
    public String purchases() {
        return "purchases-list-customer";
    }
}
