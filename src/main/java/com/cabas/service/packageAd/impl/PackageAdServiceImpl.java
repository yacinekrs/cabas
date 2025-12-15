package com.cabas.service.packageAd.impl;


import com.cabas.dto.request.packageAd.PackageAdRequest;
import com.cabas.dto.response.packageAd.PackageAdResponse;
import com.cabas.entity.PackageAd;
import com.cabas.entity.User;
import com.cabas.mapper.packageAd.PackageAdMapper;
import com.cabas.repository.PackageAdRepository;
import com.cabas.repository.UserRepository;
import com.cabas.service.packageAd.PackageAdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PackageAdServiceImpl implements PackageAdService {

    private final PackageAdRepository packageAdRepository;
    private final UserRepository userRepository;
    private final PackageAdMapper packageAdMapper;


    @Override
    @Transactional
    public PackageAdResponse createPackageAd(PackageAdRequest request, Long senderId) {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur non trouvé avec ID: " + senderId)); // Remplacé par un message plus clair

        PackageAd packageAd = packageAdMapper.toEntity(request, sender);

        PackageAd savedAd = packageAdRepository.save(packageAd);

        return packageAdMapper.toResponse(savedAd);
    }


}