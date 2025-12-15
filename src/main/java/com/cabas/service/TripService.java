package com.cabas.service;

import com.cabas.dto.request.trip.TripRequest;
import com.cabas.dto.request.trip.TripSearchCriteria;
import com.cabas.dto.request.trip.TripUpdateRequest;
import com.cabas.dto.response.trip.TripResponse;
import java.util.List;

public interface TripService {

    /**
     * F-2.2 : Crée un nouveau trajet dans la base de données.
     * @param request Le DTO d'entrée (TripRequest) contenant les détails du trajet.
     * @param carrierId L'ID du transporteur qui publie le trajet.
     * @return Le DTO de réponse (TripResponse) du trajet créé.
     */
    TripResponse createTrip(TripRequest request, Long carrierId);

    TripResponse updateTrip(TripUpdateRequest request, Long tripId);

    /**
     * F-2.4 : Recherche les trajets ouverts qui correspondent aux critères de l'expéditeur.
     * @param criteria Les critères de recherche (ville, date, poids).
     * @return Une liste de DTOs de réponse (TripResponse) des trajets disponibles.
     */
    List<TripResponse> searchTrips(TripSearchCriteria criteria);

}