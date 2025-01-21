package com.rbnb.rbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonIgnore

    private User client;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @JsonIgnore

    private Property property;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private double totalCost;

    @Enumerated(EnumType.STRING) // Store the enum as a string in the database
    @Column(nullable = false)
    private BookingStatus status;  // Possible values: PENDING, ACCEPTED, DECLINED
}
