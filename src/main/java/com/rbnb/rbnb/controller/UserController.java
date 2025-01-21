package com.rbnb.rbnb.controller;

import com.rbnb.rbnb.config.JwtService;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users") // Updated path to follow REST conventions
@RequiredArgsConstructor

public class UserController {

    @Autowired
    private UserService userService;

    private final JwtService jwtService;
    @GetMapping("/id")
    public ResponseEntity<Long> getUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        try {
            // Log the received Authorization header
            System.out.println("Received Authorization Header: " + authHeader);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                System.out.println("Invalid or missing Authorization header");
                return ResponseEntity.badRequest().build();
            }

            final String jwt = authHeader.substring(7); // Remove "Bearer " prefix
            System.out.println("Extracted JWT: " + jwt);

            Long userId = jwtService.extractUserId(jwt); // Extract user ID from the token
            System.out.println("Extracted User ID: " + userId);

            if (userId != null) {
                return ResponseEntity.ok(userId);
            } else {
                System.out.println("User ID not found in token");
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            System.out.println("Error processing request: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET /api/users/profile
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        try {
            User currentUser = userService.getCurrentUser();
            return ResponseEntity.ok(currentUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
