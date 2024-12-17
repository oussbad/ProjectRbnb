package com.rbnb.rbnb.service;
import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.Review;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PropertyService propertyService;

    public void saveReview(Long propertyId, int rating, String comment) {
        User currentUser = userService.getCurrentUser();
        Property property = propertyService.getPropertyById(propertyId);

        Review review = new Review();
        review.setUser(currentUser);
        review.setRating(rating);
        review.setComment(comment);
        review.setProperty(property);

        reviewRepository.save(review);
    }
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found with id: " + id));
    }

    public List<Review> getPropertyReviews(Long propertyId) {
        Property property = propertyService.getPropertyById(propertyId);
        return reviewRepository.findByProperty(property);
    }

    public void updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
