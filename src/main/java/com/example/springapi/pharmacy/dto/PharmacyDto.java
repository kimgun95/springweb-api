package com.example.springapi.pharmacy.dto;

import com.example.springapi.pharmacy.entity.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PharmacyDto {

    private Long id;
    private String pharmacyName;
    private String pharmacyAddress;
    private double latitude;
    private double longitude;

    private PharmacyDto(String pharmacyName, String pharmacyAddress, double latitude, double longitude) {
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static PharmacyDto of(String pharmacyName, String pharmacyAddress, double latitude, double longitude) {
        return new PharmacyDto(pharmacyName, pharmacyAddress, latitude, longitude);
    }

    public static PharmacyDto from(Pharmacy pharmacy) {
        return new PharmacyDto(
                pharmacy.getId(),
                pharmacy.getPharmacyName(),
                pharmacy.getPharmacyAddress(),
                pharmacy.getLatitude(),
                pharmacy.getLongitude()
        );
    }

//    public Pharmacy toEntity() {
//        return Pharmacy.of(
//                this.pharmacyName,
//                this.pharmacyAddress,
//                this.latitude,
//                this.longitude
//        );
//    }
}
