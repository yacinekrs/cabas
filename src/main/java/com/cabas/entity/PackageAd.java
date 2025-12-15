package com.cabas.entity;

import com.cabas.enums.AdStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private String departureCity;

    @Column(nullable = false)
    private String arrivalCity;

    @Column()
    private LocalDate departureDate;

    @Column(nullable = false)
    private Double offeredPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @OneToOne(mappedBy = "packageAd", cascade = CascadeType.ALL)
    private Booking booking;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.status = AdStatus.OPEN;
    }
}