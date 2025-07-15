package com.example.SpringbootwithDB.controllers;

import com.example.SpringbootwithDB.models.RegisterDetails;
import com.example.SpringbootwithDB.models.UserDetailsDto;
import com.example.SpringbootwithDB.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String addNewEmployee(@RequestBody UserDetailsDto register) {
        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public String login(@RequestBody RegisterDetails loginDetails) {
        return authService.authenticate(loginDetails);
    }
}
