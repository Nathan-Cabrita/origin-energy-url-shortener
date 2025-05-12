package com.nathancabrita.origin.energy.url.shortener.application.service;

import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException;
import com.nathancabrita.origin.energy.url.shortener.application.persistence.UrlStore;
import com.nathancabrita.origin.energy.url.shortener.application.validator.UrlValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService{
    private final UrlStore inMemoryUrlStore;
    private final UrlValidator urlValidator;
    private final UrlShortenerService urlShortenerService;

    @Override
    public String shortenAndStoreUrl(String url) {
        if(urlValidator.validateUrl(url)){
            String shortUrl = urlShortenerService.shortenUrl(url);
            inMemoryUrlStore.putUrl(shortUrl, url);
            return shortUrl;
        } else {
            throw new ValidationException(String.format("Url {} is not formatted correctly", url));
        }
    }
}
