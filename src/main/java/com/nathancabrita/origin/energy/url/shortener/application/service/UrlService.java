package com.nathancabrita.origin.energy.url.shortener.application.service;

import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl;

public interface UrlService {
    String shortenAndStoreLongUrl(String longUrl);
    String getLongUrl(String shortUrlKey);
    LongUrl getLongUrlInfo(String shortUrlKey);
}