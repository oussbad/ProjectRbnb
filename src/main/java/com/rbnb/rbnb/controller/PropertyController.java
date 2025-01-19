package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.dto.PropertyDto;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.service.PropertyService;
import com.rbnb.rbnb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/properties") // Utilisation d'un préfixe API
public class PropertyController {

    @Autowired
    private PropertyService propertyService;
    @Autowired
    private UserService userService;

    // Publier une nouvelle annonce
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody PropertyDto propertyDto) {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Received PropertyDto: {}", propertyDto);
        // Fetch the host by hostId
        User host = userService.findById(propertyDto.getHostId())
                .orElseThrow(() -> new RuntimeException("Host not found with ID: " + propertyDto.getHostId()));

        // Map DTO to Entity
        Property property = new Property();
        property.setTitle(propertyDto.getTitle());
        property.setDescription(propertyDto.getDescription());
        property.setAddress(propertyDto.getAddress());
        property.setCity(propertyDto.getCity());
        property.setPricePerNight(propertyDto.getPricePerNight());
        property.setBedrooms(propertyDto.getBedrooms());
        property.setImages(propertyDto.getImages());
        property.setHost(host);

        // Save the property
        Property savedProperty = propertyService.saveProperty(property);
        return ResponseEntity.ok(savedProperty);
    }


    // Modifier une annonce existante
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property property) {

        Property updatedProperty = propertyService.updateProperty(id, property);
        return ResponseEntity.ok(updatedProperty);
    }

    // Supprimer une annonce
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.ok("Property deleted successfully.");
    }

    // Visualiser les réservations pour un logement
    @GetMapping("/{id}/bookings")
    public ResponseEntity<List<?>> viewBookings(@PathVariable Long id) {
        List<?> bookings = propertyService.getBookingsForProperty(id);
        return ResponseEntity.ok(bookings);
    }

    // Rechercher des annonces avec filtres
    @GetMapping
    public ResponseEntity<List<Property>> listProperties(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer bedrooms) {
        List<Property> properties = propertyService.searchProperties(city, maxPrice, bedrooms);
        return ResponseEntity.ok(properties);
    }
    @GetMapping("/my-properties")
    public ResponseEntity<List<Property>> getPropertiesByHost(@AuthenticationPrincipal UserDetails userDetails) {
        // Get the authenticated user's email (username)
        String email = userDetails.getUsername();

        // Fetch the host user by email
        User host = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Host not found with email: " + email));

        // Fetch all properties published by the host
        List<Property> properties = propertyService.getPropertiesByHost(host);
        return ResponseEntity.ok(properties);
    }
}