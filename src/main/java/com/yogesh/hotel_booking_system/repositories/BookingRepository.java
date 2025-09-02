package com.yogesh.hotel_booking_system.repositories;

import com.yogesh.hotel_booking_system.models.Booking;
import com.yogesh.hotel_booking_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.yogesh.hotel_booking_system.models.Hotel;
import java.time.LocalDate;

// Repository for booking operations
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUser(User user); // get bookings for a user

    @Query("SELECT b FROM Booking b WHERE b.hotel = :hotel AND b.status = 'CONFIRMED' " +
            "AND b.checkIn <= :checkOut AND b.checkOut >= :checkIn")
    List<Booking> findConflictingBookings(@Param("hotel") Hotel hotel,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

    @Query("SELECT b.hotel.id FROM Booking b WHERE b.status = 'CONFIRMED' " +
            "AND b.checkIn <= :checkOut AND b.checkOut >= :checkIn")
    List<Long> findHotelIdsWithConflicts(@Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

}
