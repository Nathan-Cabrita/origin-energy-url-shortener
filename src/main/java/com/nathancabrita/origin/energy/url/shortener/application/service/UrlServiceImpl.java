package com.nathancabrita.origin.energy.url.shortener.application.service;

import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl;
import com.nathancabrita.origin.energy.url.shortener.application.domain.ShortUrl;
import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException;
import com.nathancabrita.origin.energy.url.shortener.application.persistence.UrlStore;
import com.nathancabrita.origin.energy.url.shortener.application.validator.UrlValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService{
    private final UrlStore inMemoryUrlStore;
    private final UrlValidator urlValidator;
    private final UrlShortenerService urlShortenerService;

    @Override
    public String shortenAndStoreLongUrl(String longUrl) {
        Optional<URI> longURI = urlValidator.validateUrl(longUrl);
        if(longURI.isPresent()){
            ShortUrl shortUrl = urlShortenerService.getShortUrl();
            inMemoryUrlStore.putUrl(shortUrl.shortUrlKey(), new LongUrl(longURI.get()));
            return shortUrl.toString();
        } else {
            throw new ValidationException(String.format("Url %s is not formatted correctly", longUrl));
        }
    }

    @Override
    public String getLongUrl(String shortUrlKey) {
        return inMemoryUrlStore.getLongUrl(shortUrlKey).getOriginalUrl().toString();
    }

    @Override
    public LongUrl getLongUrlInfo(String shortUrlKey) {
        return inMemoryUrlStore.getLongUrl(shortUrlKey);
    }
}
