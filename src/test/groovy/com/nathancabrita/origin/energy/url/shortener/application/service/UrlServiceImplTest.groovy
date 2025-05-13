package com.nathancabrita.origin.energy.url.shortener.application.service

import com.nathancabrita.origin.energy.url.shortener.application.BaseTest
import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException
import com.nathancabrita.origin.energy.url.shortener.application.persistence.UrlStore
import com.nathancabrita.origin.energy.url.shortener.application.validator.UrlValidator


class UrlServiceImplTest extends BaseTest {
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
        when:
        urlService.shortenAndStoreLongUrl(originalLongUrl)

        then:
        1 * urlValidator.validateUrl(originalLongUrl) >> Optional.of(longUrlURI)
        1 * urlShortenerService.getShortUrl() >> shortUrl
        1 * urlStore.putUrl(shortUrl.shortUrlKey(), _)
    }

    def "service should throw validation exception when url is invalid" (){
        when:
        urlService.shortenAndStoreLongUrl(invalidLongUrl)

        then:
        1 * urlValidator.validateUrl(invalidLongUrl) >> Optional.empty()
        0 * urlShortenerService.getShortUrl(invalidLongUrl)
        0 * urlStore.putUrl(_, invalidLongUrl)
        thrown(ValidationException)
    }
}
