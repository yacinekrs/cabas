package com.cabas.dto.request.trip;

import lombok.Data;
import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;

@Data
public class TripSearchCriteria {

    @NotBlank
    private String departureCity;

    @NotBlank
    private String arrivalCity;

    private Double basePricePerKg;

    private Double maxWeight;

    private LocalDate desiredDepartureDate;
}