package com.cabas.enums;

public enum AdStatus {

    OPEN,       // L'annonce est publiée mais n'a pas encore été prise en charge par un transporteur.
    RESERVED,      // Un transporteur a pris l'annonce.
    CANCELLED,     // L'expéditeur a annulé sa demande avant qu'elle ne soit prise en charge.
    EXPIRED        // L'annonce est restée trop longtemps en PENDING ou la date souhaitée est passée.
}