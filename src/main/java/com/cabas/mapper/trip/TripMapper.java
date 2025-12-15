package com.cabas.mapper.trip;

import com.cabas.dto.request.trip.TripRequest;
import com.cabas.dto.request.trip.TripUpdateRequest;
import com.cabas.dto.response.trip.TripResponse;
import com.cabas.entity.Trip;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TripMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "remainingWeight", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "carrier", ignore = true)
    Trip toEntity(TripRequest request);

    @Mapping(source = "carrier.id", target = "carrierName")
    TripResponse toResponse(Trip trip);

    List<TripResponse> toResponseList(List<Trip> trips);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "carrier", ignore = true)
    @Mapping(target = "remainingWeight", ignore = true)
    @Mapping(target = "departureCity", ignore = true)
    @Mapping(target = "arrivalCity", ignore = true)
    @Mapping(target = "basePricePerKg", ignore = true)
    void updateTripFromDto(TripUpdateRequest request, @MappingTarget Trip trip);
}