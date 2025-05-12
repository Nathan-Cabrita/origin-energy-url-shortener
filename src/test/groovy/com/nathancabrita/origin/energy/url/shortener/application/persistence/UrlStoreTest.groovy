package com.nathancabrita.origin.energy.url.shortener.application.persistence

import spock.lang.Specification

class UrlStoreTest extends Specification {
    private UrlStore urlStore

    def setup(){
        urlStore = new UrlStore()
    }

    def "url store can add and retrieve short url + long url"(){
        given:
        def shortUrl = "http://short.url/abc123"
        def longUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        urlStore.putUrl(shortUrl, longUrl)

        when:
        def result = urlStore.getUrl(shortUrl)

        then:
        result == longUrl
    }
}
