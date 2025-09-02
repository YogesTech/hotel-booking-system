package com.yogesh.hotel_booking_system.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

// Booking entity for hotel reservations
@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // booking ID

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // who booked

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel; // which hotel

    private LocalDate checkIn; // check-in date
    private LocalDate checkOut; // check-out date

    private String status; // PENDING, PAID, CANCELLED

}
