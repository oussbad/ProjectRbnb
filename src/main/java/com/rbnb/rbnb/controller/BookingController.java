package com.rbnb.rbnb.controller;
import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String listBookings(Model model) {
        List<Booking> bookings = bookingService.getUserBookings();
        model.addAttribute("bookings", bookings);
        return "booking-list";
    }

    @PostMapping("/new")
    public String createBooking(@RequestParam Long propertyId,
                                @RequestParam String startDate,
                                @RequestParam String endDate) {
        bookingService.createBooking(propertyId, startDate, endDate);
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}
