package com.cabas.service.packageAd;

import com.cabas.dto.request.packageAd.PackageAdRequest;

import com.cabas.dto.response.packageAd.PackageAdResponse;


public interface PackageAdService {

    PackageAdResponse createPackageAd(PackageAdRequest request, Long senderId);

}