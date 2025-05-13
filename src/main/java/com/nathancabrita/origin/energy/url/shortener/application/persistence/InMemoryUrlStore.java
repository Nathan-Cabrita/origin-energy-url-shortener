package com.nathancabrita.origin.energy.url.shortener.application.persistence;

import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl;
import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUrlStore implements UrlStore{
    private final Map<String, LongUrl> store = new ConcurrentHashMap<>();

    public void putUrl(String shortUrlKey, LongUrl longUrl) {
        store.put(shortUrlKey, longUrl);
    }

    public LongUrl getLongUrl(String shortUrlKey) {
        LongUrl longUrl = store.get(shortUrlKey);
        if(longUrl != null){
            return longUrl;
        }else {
            throw new ShortUrlNotFoundException(String.format("Short URL not found in memory store for url %s", shortUrlKey));
        }
    }
}
