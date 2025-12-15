package com.cabas.dto.response.trip;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TripResponse {
    private Long id;

    private String departureCity;

    private String arrivalCity;

    private LocalDate departureDate;

    private LocalTime departureTime;

    private Double totalWeightCapacity;

    private Double remainingWeight;

    private String carrierName;

    private String description;
}