package com.rbnb.rbnb.service;
import com.rbnb.rbnb.model.User;
import com.rbnb.rbnb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_CLIENT"); // Default role
        userRepository.save(user);
    }

    public User getCurrentUser() {
        // Implement logic to fetch currently logged-in user
        // Example: Retrieve from SecurityContextHolder
        return userRepository.findByEmail("user@example.com") // Replace with real logic
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
