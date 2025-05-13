package com.nathancabrita.origin.energy.url.shortener.application.service

import com.nathancabrita.origin.energy.url.shortener.application.BaseTest
import com.nathancabrita.origin.energy.url.shortener.application.config.UrlShortenerConfig

class UrlShortenerServiceImplTest extends BaseTest {

    private UrlShortenerConfig config

    private UrlShortenerServiceImpl urlShortenerService

    def setup(){
        config = Mock(UrlShortenerConfig)
        config.getDomain() >> "https://domain/"
        config.getShortUrlKeyLength() >> 8
        urlShortenerService = new UrlShortenerServiceImpl(config)
    }

    def "service should return short url with specified config"(){
        when:
        def result = urlShortenerService.getShortUrl()

        then:
        assert result.domain() == config.getDomain()
        assert result.shortUrlKey().length() == config.getShortUrlKeyLength()
    }
}
