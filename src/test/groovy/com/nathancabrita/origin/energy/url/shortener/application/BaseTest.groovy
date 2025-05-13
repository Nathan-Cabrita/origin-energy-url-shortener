package com.nathancabrita.origin.energy.url.shortener.application

import com.nathancabrita.origin.energy.url.shortener.application.domain.LongUrl
import com.nathancabrita.origin.energy.url.shortener.application.domain.ShortUrl
import spock.lang.Specification


class BaseTest extends Specification{
    static String originalLongUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
    static URI longUrlURI = new URL(originalLongUrl).toURI()
    def shortUrl = new ShortUrl("http://short.url/", "abc123")
    def longUrl = new LongUrl(longUrlURI)
    def invalidLongUrl = "invalidUrl"
    def shortUrlKey = "abc123"
}
