package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.BookingRepository;
import com.rbnb.rbnb.repositories.PropertyRepository;
import com.rbnb.rbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Property> searchProperties(String city, LocalDate startDate, LocalDate endDate, Double maxPrice, Integer numberOfRooms) {
        return propertyRepository.findAvailableProperties(city, startDate, endDate, maxPrice, numberOfRooms);
    }

    public Booking createReservation(Long propertyId, String startDate, String endDate) {
        // Retrieve the authenticated user's email from the security context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user by email
        User client = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Find the property by ID
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));

        // Parse the dates
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        // Calculate the total cost based on the number of nights
        long numberOfNights = end.toEpochDay() - start.toEpochDay();
        double totalCost = property.getPricePerNight() * numberOfNights;

        // Create and save the booking
        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setClient(client);
        booking.setStartDate(start);
        booking.setEndDate(end);
        booking.setTotalCost(totalCost);
        booking.setStatus("PENDING"); // Default status

        return bookingRepository.save(booking);
    }
}