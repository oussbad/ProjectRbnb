package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.BookingRepository;
import com.rbnb.rbnb.repositories.PropertyRepository;
import com.rbnb.rbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Booking> getUserBookings() {
        User currentUser = userRepository.findByEmail("user@example.com") // Replace with real logic
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return bookingRepository.findByClient(currentUser);
    }

    public void createBooking(Long propertyId, String startDate, String endDate) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        User client = userRepository.findByEmail("user@example.com") // Replace with real logic
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Booking booking = new Booking();
        booking.setProperty(property);
        booking.setClient(client);
        booking.setStartDate(LocalDate.parse(startDate));
        booking.setEndDate(LocalDate.parse(endDate));
        booking.setTotalCost(property.getPricePerNight() *
                (booking.getEndDate().toEpochDay() - booking.getStartDate().toEpochDay()));
        booking.setStatus("PENDING");
        bookingRepository.save(booking);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
        bookingRepository.delete(booking);
    }
}
