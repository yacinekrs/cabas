package com.cabas.controller;

import com.cabas.dto.request.trip.TripRequest;
import com.cabas.dto.request.trip.TripSearchCriteria;
import com.cabas.dto.request.trip.TripUpdateRequest;
import com.cabas.dto.response.trip.TripResponse;
import com.cabas.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    /**
     * F-2.2 : Endpoint de création de trajet (par le Transporteur).
     * Méthode: POST /api/v1/trips?carrierId={id}
     */
    @PostMapping
    public ResponseEntity<TripResponse> createTrip(
            @Valid @RequestBody TripRequest request,
            @RequestParam Long carrierId
    ) {
        TripResponse response = tripService.createTrip(request, carrierId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * F-2.4 : Endpoint de recherche de trajets (par l'Expéditeur).
     * Méthode: GET /api/v1/trips/search?depCity=...&arrCity=...
     */
    @GetMapping("/search")
    public ResponseEntity<List<TripResponse>> searchTrips(
            @Valid TripSearchCriteria criteria
    ) {
        List<TripResponse> trips = tripService.searchTrips(criteria);

        return ResponseEntity.ok(trips);
    }

    @PatchMapping("/{tripId}")
    public ResponseEntity<TripResponse> updateTrips(
            @PathVariable Long tripId,
            @Valid @RequestBody TripUpdateRequest request
            ) {
        TripResponse response = tripService.updateTrip(request, tripId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long tripId) {

        tripService.deleteTrip(tripId);

        return ResponseEntity.noContent().build();
    }
}