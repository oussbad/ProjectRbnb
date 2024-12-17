package com.rbnb.rbnb.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod; // Example: Stripe, PayPal

    @Column(nullable = false)
    private String paymentStatus; // Example: SUCCESS, FAILED, PENDING

    @Column(nullable = false)
    private LocalDate paymentDate = LocalDate.now();
}
