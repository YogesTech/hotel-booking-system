package com.yogesh.hotel_booking_system.controllers;

import com.yogesh.hotel_booking_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Controller for signup & login pages
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showLandingPage() {
        return "index"; // loads index.html
    }

    // Show signup page
    @GetMapping("/signup")
    public String showSignupPage() {
        return "signup"; // loads signup.html
    }

    // Handle signup form submission
    @PostMapping("/signup")
    public String registerUser(@RequestParam String username,
            @RequestParam String password,
            Model model) {

        if (userService.isUserExists(username)) {
            model.addAttribute("error", "Username already exists!");
            return "signup";
        }

        userService.registerUser(username, password, "USER"); // default role USER
        model.addAttribute("success", "Registration successful! Please login.");
        return "login"; // after signup, redirect to login page
    }

    // Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // loads login.html
    }

    // home page after login
    @GetMapping("/home")
    public String showHomePage() {
        return "home"; // loads home.html
    }

}
