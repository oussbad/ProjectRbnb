package com.rbnb.rbnb.service;

import com.rbnb.rbnb.dto.BookingResponseDTO;
import com.rbnb.rbnb.model.Booking;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    // Recherche des propriétés avec filtres
    public List<Property> searchProperties(String city, Double maxPrice, Integer bedrooms) {
        List<Property> properties = propertyRepository.findAll();

        if (city != null && !city.isEmpty()) {
            properties.removeIf(property -> !property.getCity().equalsIgnoreCase(city));
        }
        if (maxPrice != null) {
            properties.removeIf(property -> property.getPricePerNight() > maxPrice);
        }
        if (bedrooms != null) {
            properties.removeIf(property -> property.getBedrooms() < bedrooms);
        }

        return properties;
    }

    // Récupérer une propriété par ID
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found with ID: " + id));
    }

    // Ajouter une nouvelle propriété
    public Property saveProperty(Property property) {

        return propertyRepository.save(property);
    }

    // Mise à jour d'une propriété
    public Property updateProperty(Long id, Property updatedProperty) {
        Property existingProperty = getPropertyById(id);
        existingProperty.setTitle(updatedProperty.getTitle());
        existingProperty.setDescription(updatedProperty.getDescription());
        existingProperty.setAddress(updatedProperty.getAddress());
        existingProperty.setCity(updatedProperty.getCity());
        existingProperty.setPricePerNight(updatedProperty.getPricePerNight());
        existingProperty.setBedrooms(updatedProperty.getBedrooms());
        existingProperty.setImages(updatedProperty.getImages());
        return propertyRepository.save(existingProperty);
    }

    // Suppression d'une propriété
    public void deleteProperty(Long id) {
        if (!propertyRepository.existsById(id)) {
            throw new IllegalArgumentException("Property not found with ID: " + id);
        }
        propertyRepository.deleteById(id);
    }

    // Récupérer les réservations pour une propriété donnée

    public List<Property> getPropertiesByHost(User host) {
        return propertyRepository.findByHost(host); // Fetch properties by host
    }
    public List<BookingResponseDTO> getBookingsForProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found with id: " + propertyId));

        return property.getBookings().stream()
                .map(this::convertToBookingResponseDTO)
                .collect(Collectors.toList());
    }

    private BookingResponseDTO convertToBookingResponseDTO(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setId(booking.getId());
        dto.setPropertyName(booking.getProperty().getTitle());
        dto.setClientEmail(booking.getClient().getEmail());
        dto.setClientName(booking.getClient().getFirstname() + " " + booking.getClient().getLastname());
        dto.setStartDate(booking.getStartDate().toString());
        dto.setEndDate(booking.getEndDate().toString());
        dto.setTotalCost(booking.getTotalCost());
        dto.setStatus(booking.getStatus().toString());
        return dto;
    }
}
