package com.nathancabrita.origin.energy.url.shortener.application.service

import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException
import com.nathancabrita.origin.energy.url.shortener.application.persistence.UrlStore
import com.nathancabrita.origin.energy.url.shortener.application.validator.UrlValidator
import spock.lang.Specification

class UrlServiceTest extends Specification {
    private UrlService urlService
    private UrlStore urlStore
    private UrlValidator urlValidator
    private UrlShortenerService urlShortenerService

    def setup(){
        urlStore = Mock(UrlStore)
        urlValidator = Mock(UrlValidator)
        urlShortenerService = Mock(UrlShortenerService)
        urlService = new UrlServiceImpl(urlStore, urlValidator, urlShortenerService)
    }

    def "service should shorten and store urls" (){
        given:
        def url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        def shortUrl = "https://short.url"

        when:
        urlService.shortenAndStoreUrl(url)

        then:
        1 * urlValidator.validateUrl(url) >> true
        1 * urlShortenerService.getShortUrl(url) >> shortUrl
        1 * urlStore.putUrl(shortUrl, url)
    }

    def "service should throw validation exception when url is invalid" (){
        given:
        def url = "invalidURL"

        when:
        urlService.shortenAndStoreUrl(url)

        then:
        1 * urlValidator.validateUrl(url) >> false
        0 * urlShortenerService.getShortUrl(url)
        0 * urlStore.putUrl(_, url)
        thrown(ValidationException)
    }
}
