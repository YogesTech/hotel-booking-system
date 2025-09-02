package com.yogesh.hotel_booking_system.services;

import com.yogesh.hotel_booking_system.models.User;
import com.yogesh.hotel_booking_system.repositories.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

// Service to handle user registration and login logic
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register new user
    public User registerUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        // encrypt password before saving
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    // Check if username already exists
    public boolean isUserExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
