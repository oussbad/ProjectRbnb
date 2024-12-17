package com.rbnb.rbnb.repositories;

import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByCity(String city); // Add this method

    List<Property> findByHost(User host);

    List<Property> findByPricePerNightLessThanEqual(double maxPrice);

    List<Property> findByBedroomsGreaterThanEqual(int bedrooms);
}
