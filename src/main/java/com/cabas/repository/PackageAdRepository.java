package com.cabas.repository;

import com.cabas.entity.PackageAd;
import com.cabas.enums.AdStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PackageAdRepository extends JpaRepository<PackageAd, Long> {

    // Trouver toutes les annonces en attente (PENDING) pour le moteur de recherche
    List<PackageAd> findByStatus(AdStatus status);

    List<PackageAd> findBySenderId(Long senderId);
}