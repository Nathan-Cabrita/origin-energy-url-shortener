package com.nathancabrita.origin.energy.url.shortener.application.persistence;

import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUrlStore implements UrlStore{
    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void putUrl(String shortUrl, String url) {
        store.put(shortUrl, url);
    }

    public String getLongUrl(String shortUrlKey) {
        String longUrl = store.get(shortUrlKey);
        if(longUrl != null){
            return longUrl;
        }else {
            throw new ShortUrlNotFoundException(String.format("Short URL not found in memory store for url %s", shortUrlKey));
        }
    }
}
