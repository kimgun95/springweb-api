package com.example.springapi.pharmacy.repository;

import com.example.springapi.pharmacy.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
