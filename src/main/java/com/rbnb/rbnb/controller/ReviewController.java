package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.model.Review;
import com.rbnb.rbnb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public String saveReview(@RequestParam Long propertyId,
                             @RequestParam int rating,
                             @RequestParam String comment) {
        reviewService.saveReview(propertyId, rating, comment);
        return "redirect:/properties/" + propertyId;
    }

    @GetMapping("/{id}/edit")
    public String editReview(@PathVariable Long id, Model model) {
        Review review = reviewService.getReviewById(id);
        model.addAttribute("review", review);
        return "review-form";
    }

    @PostMapping("/{id}")
    public String updateReview(@PathVariable Long id, @ModelAttribute("review") Review review) {
        reviewService.updateReview(id, review);
        return "redirect:/properties/" + review.getProperty().getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "redirect:/properties";
    }
}
