package com.example.springapi.api.service

import spock.lang.Specification

import java.nio.charset.StandardCharsets


class KakaoUriBuilderServiceTest extends Specification {

    private KakaoUriBuilderService kakaoUriBuilderService

    def setup() {
        kakaoUriBuilderService = new KakaoUriBuilderService()
    }

    def "buildUriByAddressSearch - 한글 파라미터의 경우 정상적으로 인코딩"() {
        given:
        String address = "서울시 강동구"
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUriBuilderService.buildUriByAddressSearch(address)
        def decodedResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodedResult == "https://dapi.kakao.com/v2/local/search/address.json?query=서울시 강동구"
    }

    def "buildUriByCategorySearch - 요구하는 파라미터를 모두 제공시 정상적으로 인코딩"() {
        given:
        String categoryGroupCode = "PM9"
        double latitude = 37
        double longitude = 127
        double radius = 1
        def charset = StandardCharsets.UTF_8

        when:
        def uri = kakaoUriBuilderService.buildUriByCategorySearch(latitude, longitude, radius, categoryGroupCode)
        def decodedResult = URLDecoder.decode(uri.toString(), charset)

        then:
        decodedResult ==  "https://dapi.kakao.com/v2/local/search/category.json?category_group_code=PM9&x=127.0&y=37.0&radius=1000.0&sort=distance"
    }
}
