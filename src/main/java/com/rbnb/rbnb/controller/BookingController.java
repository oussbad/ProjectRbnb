package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.BookingStatus;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Endpoint to search for properties
    @GetMapping("/search")
    public List<Property> searchProperties(
            @RequestParam(required = false) String city,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer numberOfRooms) {
        return bookingService.searchProperties(city, startDate, endDate, maxPrice, numberOfRooms);
    }

    // Endpoint to create a reservation
    @PostMapping("/reserve")
    public Booking createReservation(@RequestBody Map<String, Object> requestData) {
        Long propertyId = ((Number) requestData.get("propertyId")).longValue();
        String startDate = (String) requestData.get("startDate");
        String endDate = (String) requestData.get("endDate");
        return bookingService.createReservation(propertyId, startDate, endDate);
    }
    @PutMapping("/{id}/status")
    public Booking updateBookingStatus(@PathVariable Long id, @RequestBody Map<String, String> requestData) {
        String statusStr = requestData.get("status");
        BookingStatus status = BookingStatus.valueOf(statusStr); // Convert string to enum
        return bookingService.updateBookingStatus(id, status);
    }

}