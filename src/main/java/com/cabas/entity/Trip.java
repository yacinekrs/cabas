package com.cabas.entity;

import com.cabas.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String arrivalCity;

    @Column(nullable = false)
    private LocalDate departureDate;

    @Column(nullable = false)
    private LocalTime departureTime;

    @Column(nullable = false)
    private Double totalWeightCapacity;

    @Column(nullable = false)
    private Double remainingWeight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrier_id", nullable = false)
    private User carrier;

    @Column(nullable = true, length = 500)
    private String description;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
