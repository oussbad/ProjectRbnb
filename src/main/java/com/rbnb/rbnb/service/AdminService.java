package com.rbnb.rbnb.service;

import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.PropertyRepository;
import com.rbnb.rbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Get all properties
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // Delete a property by ID
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }
}