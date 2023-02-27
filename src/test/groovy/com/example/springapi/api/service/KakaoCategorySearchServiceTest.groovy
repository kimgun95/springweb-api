package com.example.springapi.api.service

import com.example.springapi.AbstractIntegrationContainerBaseTest
import org.springframework.beans.factory.annotation.Autowired

class KakaoCategorySearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private KakaoCategorySearchService kakaoCategorySearchService

    private final double latitude = 37
    private final double longitude = 127
    private final double radius = 10

    def "파라미터값이 valid하다면, requestPharmacyCategorySearch 메소드는 정상적으로 document를 반환한다."() {
        when:
        def result = kakaoCategorySearchService.requestPharmacyCategorySearch(latitude, longitude, radius)

        then:
        result.documentList.size() > 0
        result.metaDto.totalCount > 0
        result.documentList.get(0).addressName != null
    }
}
