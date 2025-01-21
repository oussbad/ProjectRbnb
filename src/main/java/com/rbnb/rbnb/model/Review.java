package com.rbnb.rbnb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int rating; // Scale: 1 to 5

    @Column(nullable = false, length = 1000)

    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore

    private User user;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    @JsonIgnore

    private Property property;

    @Column(nullable = false)
    @JsonIgnore

    private LocalDate createdAt = LocalDate.now();
}
