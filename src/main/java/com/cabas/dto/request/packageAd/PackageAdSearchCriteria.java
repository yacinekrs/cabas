package com.cabas.dto.request.packageAd;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class PackageAdSearchCriteria {

    private String departureCity;

    private String arrivalCity;

    private Double minWeight;

    private Double minOfferedPrice;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate departureDate;
}