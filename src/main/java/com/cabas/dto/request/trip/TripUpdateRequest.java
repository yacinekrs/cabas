package com.cabas.dto.request.trip;

import lombok.Data;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TripUpdateRequest {

    @FutureOrPresent(message = "La date de départ doit être dans le futur ou aujourd'hui.")
    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalTime departureTime;

    @Positive
    @NotNull
    private Double totalWeightCapacity;

    private String description;

}