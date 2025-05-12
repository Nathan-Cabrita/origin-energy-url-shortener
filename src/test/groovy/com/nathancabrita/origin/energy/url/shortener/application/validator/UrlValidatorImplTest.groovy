package com.nathancabrita.origin.energy.url.shortener.application.validator

import spock.lang.Specification

class UrlValidatorImplTest extends Specification {
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
