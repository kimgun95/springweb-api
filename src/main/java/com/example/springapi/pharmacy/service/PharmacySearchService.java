package com.example.springapi.pharmacy.service;

import com.example.springapi.pharmacy.dto.PharmacyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacySearchService {

    private final PharmacyRepositoryService pharmacyRepositoryService;

    public List<PharmacyDto> searchPharmacyDtoList() {

        // redis

        // db
        return pharmacyRepositoryService.findAll()
                .stream()
                .map(PharmacyDto::from)
                .collect(Collectors.toList());
    }

}
