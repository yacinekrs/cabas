package com.cabas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    private Double averageRating;

    // Trajets proposés par cet utilisateur (en tant que Transporteur)
    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL)
    @Builder.Default // <-- NOUVEL AJOUT CRUCIAL
    private List<Trip> offeredTrips = new ArrayList<>(); // <-- AJOUT CRUCIAL

    // Annonces de colis publiées par cet utilisateur (en tant qu'Expéditeur)
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @Builder.Default // <-- NOUVEL AJOUT CRUCIAL
    private List<PackageAd> publishedAds = new ArrayList<>(); // <-- AJOUT CRUCIAL

    // Réservations où cet utilisateur est l'expéditeur
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @Builder.Default // <-- NOUVEL AJOUT CRUCIAL
    private List<Booking> bookingsSent = new ArrayList<>(); // <-- AJOUT CRUCIAL

    // Réservations où cet utilisateur est le transporteur
    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL)
    @Builder.Default // <-- NOUVEL AJOUT CRUCIAL
    private List<Booking> bookingsTaken = new ArrayList<>(); // <-- AJOUT CRUCIAL
}