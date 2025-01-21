package com.rbnb.rbnb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewResponseDTO {
    private Long id;
    private int rating;
    private String comment;
    private String userEmail; // Email of the user who wrote the review
    private String userName;  // Name of the user who wrote the review
    private LocalDate createdAt; // Date when the review was created
}