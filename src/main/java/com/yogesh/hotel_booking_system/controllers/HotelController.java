package com.yogesh.hotel_booking_system.controllers;

import com.yogesh.hotel_booking_system.models.Hotel;
import com.yogesh.hotel_booking_system.repositories.BookingRepository;
import com.yogesh.hotel_booking_system.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Controller for handling hotel-related pages
@Controller
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Show all hotels (for both user and admin)
    @GetMapping
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelRepository.findAll();
        model.addAttribute("hotels", hotels);
        return "hotels"; // loads hotels.html
    }

    // Show add hotel form (admin only)
    @GetMapping("/add")
    public String showAddHotelForm(Model model) {
        model.addAttribute("hotel", new Hotel());
        return "add-hotel";
    }

    // Handle add hotel form
    @PostMapping("/add")
    public String addHotel(@ModelAttribute Hotel hotel) {
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }

    // Delete hotel
    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
        return "redirect:/hotels";
    }

    // Search hotels by city
    @GetMapping("/search")
    public String searchHotels(@RequestParam String city, Model model) {
        List<Hotel> hotels = hotelRepository.findByCityContainingIgnoreCase(city);
        model.addAttribute("hotels", hotels);
        model.addAttribute("searchCity", city);
        return "hotels";
    }

    // Show edit hotel form (Admin only)
    @GetMapping("/edit/{id}")
    public String showEditHotelForm(@PathVariable Long id, Model model) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();
        model.addAttribute("hotel", hotel);
        return "edit-hotel";
    }

    // Handle edit form submission
    @PostMapping("/edit/{id}")
    public String updateHotel(@PathVariable Long id, @ModelAttribute Hotel updatedHotel) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow();

        hotel.setName(updatedHotel.getName());
        hotel.setCity(updatedHotel.getCity());
        hotel.setAddress(updatedHotel.getAddress());
        hotel.setDescription(updatedHotel.getDescription());
        hotel.setRating(updatedHotel.getRating());

        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }

    @GetMapping("/searchByDate")
    public String searchByDate(@RequestParam String checkIn,
            @RequestParam String checkOut,
            Model model) {
        LocalDate checkInDate = LocalDate.parse(checkIn);
        LocalDate checkOutDate = LocalDate.parse(checkOut);

        // Get hotels that are already booked in this range
        List<Long> conflictHotelIds = bookingRepository.findHotelIdsWithConflicts(checkInDate, checkOutDate);

        List<Hotel> hotels;
        if (conflictHotelIds.isEmpty()) {
            hotels = hotelRepository.findAll(); // all hotels are free
        } else {
            hotels = hotelRepository.findAll()
                    .stream()
                    .filter(h -> !conflictHotelIds.contains(h.getId()))
                    .toList();
        }

        model.addAttribute("hotels", hotels);
        model.addAttribute("checkIn", checkInDate);
        model.addAttribute("checkOut", checkOutDate);
        return "hotels";
    }

}
