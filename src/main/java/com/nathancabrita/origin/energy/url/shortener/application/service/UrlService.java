package com.nathancabrita.origin.energy.url.shortener.application.service;

public interface UrlService {
    String shortenAndStoreUrl(String longUrl);
    String getLongUrl(String shortUrl);
}