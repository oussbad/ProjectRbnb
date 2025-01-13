package com.rbnb.rbnb.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PropertySearchRequestDTO {
    private String city;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double maxPrice;
    private Integer numberOfRooms;
}
