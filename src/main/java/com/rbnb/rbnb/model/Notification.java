package com.rbnb.rbnb.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    @JsonIgnore
    private boolean read = false;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime timestamp;
    public Notification(User user, String message) {
        this.user = user;
        this.message = message;
        this.timestamp = LocalDateTime.now(); // Set the timestamp to the current time
        this.read = false; // Default to unread
    }
}
