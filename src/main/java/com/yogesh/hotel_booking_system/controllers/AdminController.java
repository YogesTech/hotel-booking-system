package com.yogesh.hotel_booking_system.controllers;

import com.yogesh.hotel_booking_system.repositories.UserRepository;
import com.yogesh.hotel_booking_system.repositories.HotelRepository;
import com.yogesh.hotel_booking_system.repositories.BookingRepository;
import com.yogesh.hotel_booking_system.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("hotelCount", hotelRepository.count());
        model.addAttribute("bookingCount", bookingRepository.count());
        model.addAttribute("paymentCount", paymentRepository.count());
        return "admin-dashboard";
    }
}
