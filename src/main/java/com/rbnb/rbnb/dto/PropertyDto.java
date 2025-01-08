package com.rbnb.rbnb.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {
    private String title;
    private String description;
    private String address;
    private String city;
    private double pricePerNight;
    private int bedrooms;
    private Long hostId;
    private List<String> images;
}