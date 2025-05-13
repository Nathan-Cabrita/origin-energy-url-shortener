package com.nathancabrita.origin.energy.url.shortener.application.validator

import com.nathancabrita.origin.energy.url.shortener.application.BaseTest

class UrlValidatorImplTest extends BaseTest {
    private UrlValidatorImpl urlValidator

    def setup() {
        urlValidator = new UrlValidatorImpl()
    }

    def "it should return if urls are valid"() {
        expect:
        urlValidator.validateUrl(url) == result

        where:
        url                                           | result
        "https://www.youtube.com/watch?v=dQw4w9WgXcQ" | true
        "invalidUrl"                                  | false
    }
}
