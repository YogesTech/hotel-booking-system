package com.yogesh.hotel_booking_system.models;

import jakarta.persistence.*;
import lombok.*;

// User entity for storing login and signup info
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique ID for each user

    @Column(nullable = false, unique = true)
    private String username; // username must be unique

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; // USER or ADMIN
}
