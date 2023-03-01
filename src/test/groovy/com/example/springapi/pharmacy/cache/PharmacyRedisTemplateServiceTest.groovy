package com.example.springapi.pharmacy.cache

import com.example.springapi.AbstractIntegrationContainerBaseTest
import com.example.springapi.pharmacy.dto.PharmacyDto
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRedisTemplateServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRedisTemplateService pharmacyRedisTemplateService

    def setup() {
        pharmacyRedisTemplateService.findAll()
                .forEach(dto -> {
                    pharmacyRedisTemplateService.delete(dto.getId())
                })
    }

    def "save success - 여러개의 pharmacyDto 저장에 성공한다"() {
        given:
        String pharmacyName = "name"
        String pharmacyAddress = "address"
        PharmacyDto dto =
                PharmacyDto.builder()
                        .id(1L)
                        .pharmacyName(pharmacyName)
                        .pharmacyAddress(pharmacyAddress)
                        .build()
        String pharmacyName1 = "name1"
        String pharmacyAddress1 = "address1"
        PharmacyDto dto1 =
                PharmacyDto.builder()
                        .id(2L)
                        .pharmacyName(pharmacyName1)
                        .pharmacyAddress(pharmacyAddress1)
                        .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        pharmacyRedisTemplateService.save(dto1)
        List<PharmacyDto> result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 2
        result.get(0).id == 1L
        result.get(0).pharmacyName == pharmacyName
        result.get(0).pharmacyAddress == pharmacyAddress
        result.get(1).id == 2L
        result.get(1).pharmacyName == pharmacyName1
        result.get(1).pharmacyAddress == pharmacyAddress1
    }

    def "findAll success - redis에 data가 없다면 empty list를 반환한다"() {
        when:
        List<PharmacyDto> result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

    def "save fail - pharmacyDto에 내용이 없다면 save에 실패한다"() {
        given:
        PharmacyDto dto =
                PharmacyDto.builder()
                        .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        List<PharmacyDto> result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

    def "delete"() {
        given:
        String pharmacyName = "name"
        String pharmacyAddress = "address"
        PharmacyDto dto =
                PharmacyDto.builder()
                        .id(1L)
                        .pharmacyName(pharmacyName)
                        .pharmacyAddress(pharmacyAddress)
                        .build()

        when:
        pharmacyRedisTemplateService.save(dto)
        pharmacyRedisTemplateService.delete(dto.getId())
        def result = pharmacyRedisTemplateService.findAll()

        then:
        result.size() == 0
    }

}
