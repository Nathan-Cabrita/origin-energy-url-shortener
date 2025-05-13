package com.nathancabrita.origin.energy.url.shortener.application

import spock.lang.Specification

class BaseTest extends Specification{
    def shortUrl = "http://short.url/abc123"
    def longUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
    def invalidLongUrl = "invalidUrl"
    def shortUrlKey = "abc123"
}
