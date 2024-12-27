package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/debug")
    public String debugUsers() {
        return userDetailsService.toString();
    }
}
