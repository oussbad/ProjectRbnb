package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> searchProperties(String city, Double maxPrice, Integer bedrooms) {
        // Apply filters if provided
        List<Property> properties = propertyRepository.findAll();

        if (city != null && !city.isEmpty()) {
            properties = propertyRepository.findByCity(city);
        }
        if (maxPrice != null) {
            properties.removeIf(property -> property.getPricePerNight() > maxPrice);
        }
        if (bedrooms != null) {
            properties.removeIf(property -> property.getBedrooms() < bedrooms);
        }

        return properties;
    }

    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
    }

    public void saveProperty(Property property) {
        propertyRepository.save(property);
    }

    public void updateProperty(Long id, Property updatedProperty) {
        Property existingProperty = getPropertyById(id);
        existingProperty.setTitle(updatedProperty.getTitle());
        existingProperty.setDescription(updatedProperty.getDescription());
        existingProperty.setPricePerNight(updatedProperty.getPricePerNight());
        existingProperty.setBedrooms(updatedProperty.getBedrooms());
        propertyRepository.save(existingProperty);
    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}
