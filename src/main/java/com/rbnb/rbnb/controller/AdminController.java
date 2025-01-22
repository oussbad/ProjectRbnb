package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.dto.ReviewResponseDTO;
import com.rbnb.rbnb.dto.UserResponseDTO;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.service.AdminService;
import com.rbnb.rbnb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ReviewService reviewService;

    // Get all users
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Delete a user by ID
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all properties
    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = adminService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    // Delete a property by ID
    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        adminService.deleteProperty(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all reviews for a property
    @GetMapping("/properties/{propertyId}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByPropertyId(@PathVariable Long propertyId) {
        List<ReviewResponseDTO> reviews = reviewService.getPropertyReviews(propertyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    // Delete a review by ID
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}