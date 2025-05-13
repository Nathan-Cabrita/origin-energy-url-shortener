package com.nathancabrita.origin.energy.url.shortener.application.persistence;

import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl;

public interface UrlStore {
    void putUrl(String shortUrl, LongUrl longUrl);
    LongUrl getLongUrl(String shortUrlKey);
}
