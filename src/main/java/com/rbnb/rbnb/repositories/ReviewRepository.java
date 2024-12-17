package com.rbnb.rbnb.repositories;


import com.rbnb.rbnb.model.Property;
import com.rbnb.rbnb.model.Review;
import com.rbnb.rbnb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProperty(Property property); // Find reviews by property
    List<Review> findByUser(User user); // Find reviews by user
    List<Review> findByRatingGreaterThanEqual(int rating); // Find reviews by rating
}
