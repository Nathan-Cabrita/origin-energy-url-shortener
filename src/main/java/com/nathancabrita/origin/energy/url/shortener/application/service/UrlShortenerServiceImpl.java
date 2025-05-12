package com.nathancabrita.origin.energy.url.shortener.application.service;

import com.nathancabrita.origin.energy.url.shortener.application.config.UrlShortenerConfig;
import com.nathancabrita.origin.energy.url.shortener.application.domain.ShortUrl;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Data
public class UrlShortenerServiceImpl implements UrlShortenerService{
    private final UrlShortenerConfig urlShortenerConfig;

    @Override
    public ShortUrl getShortUrl() {
        return new ShortUrl(urlShortenerConfig.getDomain(), RandomStringUtils.randomAlphanumeric(urlShortenerConfig.getShortUrlKeyLength()));
    }
}
