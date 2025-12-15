package com.cabas.repository;

import com.cabas.entity.Trip;
import com.cabas.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("""
        SELECT t FROM Trip t
        WHERE t.status = :status
          AND t.departureCity = :depCity
          AND t.arrivalCity = :arrCity
          AND t.departureDate >= :minDate
          AND t.remainingWeight >= :minWeight
          AND t.basePricePerKg <= :maxPrice
        ORDER BY t.departureDate ASC
    """)
    List<Trip> findAvailableTrips(
            @Param("depCity") String departureCity,
            @Param("arrCity") String arrivalCity,
            @Param("minDate") LocalDate minimumDepartureDate,
            @Param("minWeight") double minimumWeight,
            @Param("status") TripStatus status,
            @Param("maxPrice") Double maxPrice
    );

}