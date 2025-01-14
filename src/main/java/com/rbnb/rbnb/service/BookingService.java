package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.BookingStatus;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.BookingRepository;
import com.rbnb.rbnb.repositories.PropertyRepository;
import com.rbnb.rbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
        booking.setStatus(BookingStatus.PENDING); // Default status

        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long bookingId, BookingStatus status) {
        // Retrieve the authenticated user's email from the security context
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the authenticated user by email
        User authenticatedUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Find the booking by ID
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));

        // Get the property associated with the booking
        Property property = booking.getProperty();

        // Get the host of the property
        User host = property.getHost();

        // Check if the authenticated user is the host
        if (!host.getId().equals(authenticatedUser.getId())) {
            throw new AccessDeniedException("You are not authorized to update this booking.");
        }

        // Update the booking status
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

}