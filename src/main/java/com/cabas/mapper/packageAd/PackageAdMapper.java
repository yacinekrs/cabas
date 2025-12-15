package com.cabas.mapper.packageAd;

import com.cabas.dto.request.packageAd.PackageAdRequest;
import com.cabas.dto.response.packageAd.PackageAdResponse;
import com.cabas.entity.PackageAd;
import com.cabas.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PackageAdMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "booking", ignore = true)
    @Mapping(target = "sender", source = "sender")
    PackageAd toEntity(PackageAdRequest request, User sender);

    @Mapping(source = "sender.firstName", target = "senderName")
    PackageAdResponse toResponse(PackageAd packageAd);

    List<PackageAdResponse> toResponseList(List<PackageAd> packageAds);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "sender", ignore = true)
    @Mapping(target = "booking", ignore = true)
    void updatePackageAdFromDto(PackageAdRequest request, @MappingTarget PackageAd packageAd);
}