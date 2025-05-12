package com.nathancabrita.origin.energy.url.shortener.application.persistence;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UrlStore {
    private final Map<String, String> store = new ConcurrentHashMap<>();

    public void putUrl(String shortUrl, String url) {
        store.put(shortUrl, url);
    }

    public String getUrl(String key) {
        return store.get(key);
    }
}
