package com.rbnb.rbnb.dto;

import lombok.Data;

@Data
public class BookingResponseDTO {
    private Long id;
    private String propertyName;
    private String clientEmail;
    private String clientName; // Add this field
    private String startDate;
    private String endDate;
    private Double totalCost;
    private String status;
}