package com.example.springapi.direction.service

import com.example.springapi.AbstractIntegrationContainerBaseTest
import com.example.springapi.api.dto.DocumentDto
import org.springframework.beans.factory.annotation.Autowired

class DirectionByCategorySearchServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private DirectionService directionService

    def "buildDirectionListByCategoryApi - 결과 값이 거리 순으로 정렬이 되는지 확인"() {
        given:
        def addressName = "서울 성북구 종암로10길"
        double inputLatitude = 37.5960650456809
        double inputLongitude = 127.037033003036

        def inputDocumentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        //임의로 List<Distance>를 만들어 stubbing할 수 있지만 실제 데이터로 비교하고 싶었음
        def results = directionService.buildDirectionListByCategoryApi(inputDocumentDto)

        then:
        //약국을 최대 3개만 받아오기 때문에 그 안에서 비교해야 함
        results.get(0).distance <= results.get(1).distance
        results.get(1).distance <= results.get(2).distance
    }
}
