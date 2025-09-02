package com.yogesh.hotel_booking_system.models;

import jakarta.persistence.*;
import lombok.*;

// Hotel entity for storing hotel details
@Entity
@Table(name = "hotels")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // unique hotel ID

    @Column(nullable = false)
    private String name; // hotel name

    @Column(nullable = false)
    private String city; // hotel city

    private String address;

    private String description;

    private double rating; // hotel rating (0-5)
}
