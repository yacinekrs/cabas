package com.cabas.enums;

public enum TripStatus {

    OPEN,
    FULL,       // Le trajet est complet (poids restant = 0), on ne peut plus réserver.
    EXPIRED,  // La date d'arrivée est passée, le trajet est terminé (archivé).
    CANCELLED
}