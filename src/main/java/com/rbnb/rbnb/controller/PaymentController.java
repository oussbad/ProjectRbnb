package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{bookingId}")
    public String processPayment(@PathVariable Long bookingId, @RequestParam double amount) {
        paymentService.processPayment(bookingId, amount);
        return "redirect:/bookings";
    }
}
