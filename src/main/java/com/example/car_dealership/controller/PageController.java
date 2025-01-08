package com.example.car_dealership.controller;

import com.example.car_dealership.model.DealerShip;
import com.example.car_dealership.model.InternalUser;
import com.example.car_dealership.repository.DealershipRepository;
import com.example.car_dealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DealershipRepository dealershipRepository;

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
        String username = principal.getName();
        Optional<InternalUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        if (Objects.equals(user.get().getRole(), "customer")) {
            return "redirect:/customer/dashboard";
        }

        if (Objects.equals(user.get().getRole(), "dealership")) {
            return "redirect:/dealership/dashboard";
        }

        return "";
    }

    @GetMapping("/dealership/dashboard")
    public String dealershipDashboard(Principal principal, Model model) {
        String username = principal.getName();
        Optional<InternalUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        model.addAttribute("currentUsername", username);
        model.addAttribute("role", user.get().getRole());

        return "dashboard-dealership";
    }

    @GetMapping("/dealership/dashboard/add-car")
    public String addCar(Principal principal, Model model) {
        String username = principal.getName();
        Optional<InternalUser> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        DealerShip dealership = dealershipRepository.findByUserId(user.get().getId());

        model.addAttribute("currentUsername", username);
        model.addAttribute("role", user.get().getRole());
        model.addAttribute("dealershipId", dealership.getId());

        return "add-a-car";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard() {
        return "dashboard-customer";
    }
}
