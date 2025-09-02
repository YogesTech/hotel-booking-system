package com.yogesh.hotel_booking_system.repositories;

import com.yogesh.hotel_booking_system.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository to handle DB operations for User
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // for login check
}
