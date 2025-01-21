package com.rbnb.rbnb.service;

import com.rbnb.rbnb.dto.ReviewResponseDTO;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.Review;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    // Save a review
    public Review saveReview(Long propertyId, int rating, String comment) {
        User currentUser = userService.getCurrentUser();
        Property property = propertyService.getPropertyById(propertyId);

        Review review = new Review();
        review.setUser(currentUser);
        review.setRating(rating);
        review.setComment(comment);
        review.setProperty(property);

        return reviewRepository.save(review);
    }

    // Get a review by ID
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + id));
    }

    // Get all reviews for a property


    // Update a review
    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    // Delete a review
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
    public List<ReviewResponseDTO> getPropertyReviews(Long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        return reviewRepository.findByProperty(property).stream()
                .map(this::convertToReviewResponseDTO)
                .collect(Collectors.toList());
    }

    // Convert a Review entity to a ReviewResponseDTO
    private ReviewResponseDTO convertToReviewResponseDTO(Review review) {
        ReviewResponseDTO dto = new ReviewResponseDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setUserEmail(review.getUser().getEmail());
        dto.setUserName(review.getUser().getFirstname() + " " + review.getUser().getLastname());
        dto.setCreatedAt(review.getCreatedAt());
        return dto;
    }
}