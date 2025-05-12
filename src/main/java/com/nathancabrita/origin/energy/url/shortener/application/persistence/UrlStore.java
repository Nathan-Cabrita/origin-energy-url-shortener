package com.nathancabrita.origin.energy.url.shortener.application.persistence;

public interface UrlStore {
    void putUrl(String shortUrl, String url);
    String getUrl(String key);
}
