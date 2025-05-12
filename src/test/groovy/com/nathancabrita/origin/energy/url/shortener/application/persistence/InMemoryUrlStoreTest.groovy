package com.nathancabrita.origin.energy.url.shortener.application.persistence

import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException
import spock.lang.Specification

class InMemoryUrlStoreTest extends Specification {
    private InMemoryUrlStore urlStore

    def setup(){
        urlStore = new InMemoryUrlStore()
    }

    def "url store throws ShortUrlNotFoundException when short url is not in the memory store"(){
        given:
        def shortUrl = "http://short.url/notfound"

        when:
        urlStore.getLongUrl(shortUrl)

        then:
        thrown (ShortUrlNotFoundException)
    }

    def "url store can add and retrieve short url + long url"(){
        given:
        def shortUrl = "http://short.url/abc123"
        def longUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        urlStore.putUrl(shortUrl, longUrl)

        when:
        def result = urlStore.getLongUrl(shortUrl)

        then:
        result == longUrl
    }
}
