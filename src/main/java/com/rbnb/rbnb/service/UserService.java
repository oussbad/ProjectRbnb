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
        //user.setRole("ROLE_CLIENT"); // Default role
        userRepository.save(user);
    }

    public User authenticateUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return user; // Successfully authenticated
    }


    public User getCurrentUser() {
        // Implement logic to fetch currently logged-in user
        // Example: Retrieve from SecurityContextHolder
        return userRepository.findByEmail("user@example.com") // Replace with real logic
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
