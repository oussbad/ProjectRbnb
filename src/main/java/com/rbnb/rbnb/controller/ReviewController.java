package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.dto.ReviewRequest;
import com.rbnb.rbnb.dto.ReviewResponseDTO;
import com.rbnb.rbnb.model.Review;
import com.rbnb.rbnb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Save a new review
    @PostMapping
    public ResponseEntity<Review> saveReview(@RequestBody ReviewRequest reviewRequest) {
        Review savedReview = reviewService.saveReview(
                reviewRequest.getPropertyId(),
                reviewRequest.getRating(),
                reviewRequest.getComment()
        );
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    // Get a review by ID
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    // Update a review
    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id,
                                               @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    // Delete a review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get all reviews for a property
    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ReviewResponseDTO>> getReviewsByPropertyId(@PathVariable Long propertyId) {
        List<ReviewResponseDTO> reviews = reviewService.getPropertyReviews(propertyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}