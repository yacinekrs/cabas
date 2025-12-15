package com.cabas.entity;

import com.cabas.enums.AdStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "package_ads")
public class PackageAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private Double weight; // poids

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String arrivalCity;

    private LocalDate departureDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // Relation : Une annonce (PackageAd) a au maximum une réservation (Booking) concrète.
    @OneToOne(mappedBy = "packageAd", cascade = CascadeType.ALL)
    private Booking booking;
}
