package com.yogesh.hotel_booking_system.controllers;

import com.yogesh.hotel_booking_system.models.Booking;
import com.yogesh.hotel_booking_system.models.Hotel;
import com.yogesh.hotel_booking_system.models.User;
import com.yogesh.hotel_booking_system.repositories.BookingRepository;
import com.yogesh.hotel_booking_system.repositories.HotelRepository;
import com.yogesh.hotel_booking_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Controller for booking operations
@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    // Show user bookings
    @GetMapping
    public String viewMyBookings(Authentication authentication, Model model) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Booking> bookings = bookingRepository.findByUser(user);
        model.addAttribute("bookings", bookings);
        return "bookings"; // loads bookings.html
    }

    // Show booking form for a hotel
    @GetMapping("/new/{hotelId}")
    public String showBookingForm(@PathVariable Long hotelId, Model model) {
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        model.addAttribute("hotel", hotel);
        return "book-hotel";
    }

    // Handle booking submission
    @PostMapping("/new/{hotelId}")
    public String makeBooking(@PathVariable Long hotelId,
            @RequestParam String checkIn,
            @RequestParam String checkOut,
            Authentication authentication,
            Model model) {

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow();
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username).orElseThrow();

        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);

        // Check for conflicts (PENDING or PAID handled in repository query)
        List<Booking> conflicts = bookingRepository.findConflictingBookings(hotel, checkInDate, checkOutDate);

        if (!conflicts.isEmpty()) {
            model.addAttribute("hotel", hotel);
            model.addAttribute("error", "Selected dates are not available. Please choose different dates.");
            return "book-hotel";
        }

        // If no conflict, then proceed
        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(user);
        booking.setCheckIn(checkInDate);
        booking.setCheckOut(checkOutDate);
        booking.setStatus("PENDING"); // only pending at first
        bookingRepository.save(booking);

        // redirect to payment page
        return "redirect:/payments/new/" + booking.getId();
    }

    // Cancel a booking (USER only)
    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id, Authentication authentication) {
        Booking booking = bookingRepository.findById(id).orElseThrow();

        // ensure user can only cancel their own booking
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        if (!booking.getUser().getUsername().equals(username)) {
            return "redirect:/bookings?error=not_authorized";
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        return "redirect:/bookings?success=cancelled";
    }

}
