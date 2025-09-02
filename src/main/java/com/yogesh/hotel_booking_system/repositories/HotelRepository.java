package com.yogesh.hotel_booking_system.repositories;

import com.yogesh.hotel_booking_system.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Repository for hotel DB operations
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCityContainingIgnoreCase(String city); // for search feature
}
