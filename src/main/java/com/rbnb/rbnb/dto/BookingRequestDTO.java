package com.rbnb.rbnb.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Long propertyId;
    private String startDate;
    private String endDate;
}
