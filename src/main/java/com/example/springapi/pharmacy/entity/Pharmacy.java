package com.example.springapi.pharmacy.entity;

import com.example.springapi.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "pharmacy")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pharmacyName;
    private String pharmacyAddress;
    private double latitude;
    private double longitude;

    private Pharmacy(String pharmacyName, String pharmacyAddress, double latitude, double longitude) {
        this.pharmacyName = pharmacyName;
        this.pharmacyAddress = pharmacyAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static Pharmacy of(String pharmacyName, String pharmacyAddress, double latitude, double longitude) {
        return new Pharmacy(pharmacyName, pharmacyAddress, latitude, longitude);
    }

    public void changePharmacyAddress(String address) {
        this.pharmacyAddress = address;
    }

}
