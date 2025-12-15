package com.cabas.controller.packageAd;


import com.cabas.dto.request.packageAd.PackageAdRequest;
import com.cabas.dto.response.packageAd.PackageAdResponse;
import com.cabas.service.packageAd.PackageAdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/package-ads")
@RequiredArgsConstructor
public class PackageAdController {

    private final PackageAdService packageAdService;

    /**
     * Endpoint POST pour la création d'une nouvelle annonce de colis par un expéditeur.
     * * @param senderId L'ID de l'utilisateur qui crée l'annonce (expéditeur).
     * @param request Le DTO de requête contenant les données du colis (validé par @Valid).
     * @return ResponseEntity contenant l'objet PackageAdResponse et le statut HTTP 201 CREATED.
     */
    @PostMapping("/senders/{senderId}")
    public ResponseEntity<PackageAdResponse> createPackageAd(
            @PathVariable Long senderId,
            @Valid @RequestBody PackageAdRequest request)
    {

        PackageAdResponse response = packageAdService.createPackageAd(request, senderId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Les autres méthodes (GET, PUT, DELETE, SEARCH) pour le CRUD seront ajoutées ici.
}