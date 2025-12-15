package com.cabas.dto.request.trip;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TripRequest {

    @NotBlank
    private String departureCity;

    @NotBlank
    private String arrivalCity;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @NotNull
    @Positive
    private Double totalWeightCapacity;

    private String description;

}