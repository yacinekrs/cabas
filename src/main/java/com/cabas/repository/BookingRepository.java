package com.cabas.repository;

import com.cabas.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Trouver toutes les réservations faites par un expéditeur
    List<Booking> findBySenderId(Long senderId);

    // Trouver toutes les réservations prises en charge par un transporteur (pour son historique)
    List<Booking> findByCarrierId(Long carrierId);

    // Trouver toutes les réservations liées à un trajet spécifique
    List<Booking> findByTripId(Long tripId);
}