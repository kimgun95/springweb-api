package com.example.springapi.pharmacy.service

import com.example.springapi.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class PharmacyRecommendationServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRecommendationService pharmacyRecommendationService

    def "recommendPharmacyList - 주소 입력시 가까운 약국을 데이터로 가져오는지 확인"() {
        given:
        def addressName = "서울 성북구 종암로10길"

        when:
        def results = pharmacyRecommendationService.recommendPharmacyList(addressName)

        then:
        results.get(0).distance <= results.get(1).distance
        results.get(1).distance <= results.get(2).distance
    }
}
