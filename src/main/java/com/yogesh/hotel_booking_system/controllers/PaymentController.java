package com.yogesh.hotel_booking_system.controllers;

import com.yogesh.hotel_booking_system.models.Booking;
import com.yogesh.hotel_booking_system.models.Payment;
import com.yogesh.hotel_booking_system.repositories.BookingRepository;
import com.yogesh.hotel_booking_system.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    // Show payment page for a booking
    @GetMapping("/new/{bookingId}")
    public String showPaymentPage(@PathVariable Long bookingId, Model model) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        model.addAttribute("booking", booking);
        return "payment";
    }

    // Handle payment (simulation)
    @PostMapping("/new/{bookingId}")
    public String makePayment(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();

        // create a payment record
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(1000.00); // fixed amount for demo
        payment.setStatus("SUCCESS");

        paymentRepository.save(payment);

        // update booking status
        booking.setStatus("PAID");
        bookingRepository.save(booking);

        return "redirect:/bookings";
    }
}
