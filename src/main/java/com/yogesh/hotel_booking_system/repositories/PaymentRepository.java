package com.yogesh.hotel_booking_system.repositories;

import com.yogesh.hotel_booking_system.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
