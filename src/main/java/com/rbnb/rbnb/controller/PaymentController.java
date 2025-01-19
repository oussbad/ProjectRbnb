package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.model.Payment;
import com.rbnb.rbnb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Use @RestController instead of @Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{bookingId}")
    public Payment processPayment(@PathVariable Long bookingId, @RequestParam double amount) {
        return paymentService.processPayment(bookingId, amount); // Return the Payment object
    }
}