package com.rbnb.rbnb.repositories;


import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByBooking(Booking booking);

    List<Payment> findByPaymentStatus(String status);
}
