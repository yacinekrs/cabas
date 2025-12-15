package com.cabas.service.impl;

import com.cabas.dto.request.trip.TripRequest;
import com.cabas.dto.request.trip.TripSearchCriteria;
import com.cabas.dto.request.trip.TripUpdateRequest;
import com.cabas.dto.response.trip.TripResponse; // NOUVEAU
import com.cabas.entity.Trip;
import com.cabas.entity.User; // Assurez-vous d'avoir l'Entité User
import com.cabas.enums.TripStatus; // Assurez-vous d'avoir l'Enum TripStatus
import com.cabas.mapper.TripMapper; // NOUVEAU
import com.cabas.repository.TripRepository;
import com.cabas.repository.UserRepository; // Assurez-vous d'avoir le UserRepository
import com.cabas.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripMapper tripMapper;

    @Override
    @Transactional
    public TripResponse createTrip(TripRequest request, Long carrierId) {

        User carrier = userRepository.findById(carrierId)
                .orElseThrow(() -> new RuntimeException("Transporteur non trouvé avec l'ID: " + carrierId));

        Trip newTrip = tripMapper.toEntity(request);

        newTrip.setRemainingWeight(request.getTotalWeightCapacity());
        newTrip.setStatus(TripStatus.OPEN);
        newTrip.setCarrier(carrier);

        Trip savedTrip = tripRepository.save(newTrip);

        return tripMapper.toResponse(savedTrip);
    }


    @Override
    @Transactional
    public TripResponse updateTrip(TripUpdateRequest request, Long tripId){

        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé avec l'ID: " + tripId));

        if (existingTrip.getStatus() != TripStatus.OPEN){
            throw new RuntimeException("Seuls les trajets en statut OPEN peuvent être modifier");
        }

        // TODO: [Future Implementation] Remplacer ce '0.0' par la SOMME RÉELLE DES POIDS RÉSERVÉS.
        double totalWeightReserved = 0.0;
        double newTotalCapacity = request.getTotalWeightCapacity();
        if (newTotalCapacity < totalWeightReserved) {
            throw new RuntimeException(
                    "La nouvelle capacité totale (" + newTotalCapacity + " kg) ne peut pas être inférieure " +
                            "au poids total déjà réservé (" + totalWeightReserved + " kg)."
            );
        }
        double newRemainingWeight = newTotalCapacity - totalWeightReserved;
        existingTrip.setRemainingWeight(newRemainingWeight);

        tripMapper.updateTripFromDto(request, existingTrip);

        Trip savedTrip = tripRepository.save(existingTrip);
        return tripMapper.toResponse(savedTrip);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripResponse> searchTrips(
            TripSearchCriteria criteria
    ){
        double minWeightRequired = criteria.getMaxWeight() != null ? criteria.getMaxWeight() : 0.0;
        LocalDate searchDate = criteria.getDesiredDepartureDate();
        if (searchDate == null || searchDate.isBefore(LocalDate.now())) {
            searchDate = LocalDate.now();
        }

        double maxPrice = criteria.getBasePricePerKg() != null ? criteria.getBasePricePerKg() : Double.MAX_VALUE;

        List<Trip> availableTrips = tripRepository.findAvailableTrips(
                criteria.getDepartureCity(),
                criteria.getArrivalCity(),
                searchDate,
                minWeightRequired,
                TripStatus.OPEN,
                maxPrice
                );

      return tripMapper.toResponseList(availableTrips);
    }

    @Override
    @Transactional
    public void deleteTrip(Long tripId) {
        Trip existingTrip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trajet non trouvé avec l'ID: " + tripId));

        // TODO: [À implémenter après Booking]
        // AVANT la suppression, on doit VÉRIFIER
        // s'il existe des réservations (Bookings) ACCEPTÉES pour ce trajet.
        // Si oui, la suppression devrait être interdite, ou le statut du Trip
        // devrait être passé à CANCELLED avec envoi de notifications aux expéditeurs,
        // et application éventuelle de pénalités.

        tripRepository.delete(existingTrip);
    }

}