package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Payment;
import com.rbnb.rbnb.repositories.BookingRepository;
import com.rbnb.rbnb.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public Payment processPayment(Long bookingId, double amount) {
        // Fetch Booking
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid booking ID"));

        // Create Payment
        Payment payment = new Payment();
        payment.setBooking(booking); // Set the Booking entity
        payment.setAmount(amount);
        payment.setPaymentMethod("Stripe"); // Example method
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDate(LocalDate.now());

        // Save Payment and return it
        return paymentRepository.save(payment);
    }
}