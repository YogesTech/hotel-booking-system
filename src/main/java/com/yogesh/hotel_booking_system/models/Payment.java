package com.yogesh.hotel_booking_system.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking; // each payment belongs to one booking

    private double amount; // fixed or calculated
    private String status; // SUCCESS / FAILED
}
