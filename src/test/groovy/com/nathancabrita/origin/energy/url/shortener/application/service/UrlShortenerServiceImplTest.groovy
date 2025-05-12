package com.nathancabrita.origin.energy.url.shortener.application.service

import com.nathancabrita.origin.energy.url.shortener.application.config.UrlShortenerConfig
import spock.lang.Specification

class UrlShortenerServiceImplTest extends Specification {

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
        assert result.length() == config.getDomain().length() + config.getShortUrlKeyLength()
        assert result.startsWith(config.getDomain())
    }
}
