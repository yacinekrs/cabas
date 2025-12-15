package com.cabas.dto.response.packageAd;

import com.cabas.enums.AdStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PackageAdResponse {

    private Long id;

    private String senderName;

    private String departureCity;
    private String arrivalCity;

    private Double weight;
    private Double offeredPrice;

    private LocalDate departureDate;
    private String description;

    private AdStatus status;
    private LocalDateTime creationDate;
}

