package com.rbnb.rbnb.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name; // Combined firstname and lastname
    private String email;
    private String role; // Role of the user (e.g., ROLE_HOST, ROLE_CLIENT, ROLE_ADMIN)
}