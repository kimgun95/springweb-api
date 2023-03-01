package com.example.springapi.pharmacy.service

import com.example.springapi.pharmacy.cache.PharmacyRedisTemplateService
import com.example.springapi.pharmacy.dto.PharmacyDto
import com.example.springapi.pharmacy.entity.Pharmacy
import com.google.common.collect.Lists
import spock.lang.Specification

class PharmacySearchServiceTest extends Specification {

    private PharmacySearchService pharmacySearchService

    private PharmacyRepositoryService pharmacyRepositoryService = Mock()
    private PharmacyRedisTemplateService pharmacyRedisTemplateService = Mock()

    private List<Pharmacy> pharmacyList
    private List<PharmacyDto> pharmacyDtoList

    def setup() {
        pharmacySearchService = new PharmacySearchService(pharmacyRepositoryService, pharmacyRedisTemplateService)

        pharmacyList = Lists.newArrayList(
                Pharmacy.builder()
                        .id(1L)
                        .pharmacyName("호수온누리약국")
                        .latitude(37.60894036)
                        .longitude(127.029052)
                        .build(),
                Pharmacy.builder()
                        .id(2L)
                        .pharmacyName("돌곶이온누리약국")
                        .latitude(37.61040424)
                        .longitude(127.0569046)
                        .build())

        pharmacyDtoList = Lists.newArrayList(
                PharmacyDto.builder()
                        .id(1L)
                        .pharmacyName("호수온누리약국")
                        .pharmacyAddress("주소1")
                        .build(),
                PharmacyDto.builder()
                        .id(2L)
                        .pharmacyName("돌곶이온누리약국")
                        .pharmacyAddress("주소2")
                        .build(),
                PharmacyDto.builder()
                        .id(3L)
                        .pharmacyName("우주약국")
                        .pharmacyAddress("주소3")
                        .build()
        )
    }

    def "redis에 data가 존재한다면 redis db를 이용해 데이터 조회"() {
        when:
        pharmacyRedisTemplateService.findAll() >> pharmacyDtoList
        pharmacyRepositoryService.findAll() >> pharmacyList

        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 3
    }

    def "레디스 장애시 DB를 이용하여 약국 데이터 조회"() {
        when:
        pharmacyRedisTemplateService.findAll() >> []
        pharmacyRepositoryService.findAll() >> pharmacyList

        def result = pharmacySearchService.searchPharmacyDtoList()

        then:
        result.size() == 2
    }

}
