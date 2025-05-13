package com.nathancabrita.origin.energy.url.shortener.application.persistence

import com.nathancabrita.origin.energy.url.shortener.application.BaseTest
import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException

class InMemoryUrlStoreTest extends BaseTest {
    private InMemoryUrlStore urlStore

    def setup(){
        urlStore = new InMemoryUrlStore()
    }

    def "url store throws ShortUrlNotFoundException when short url is not in the memory store"(){
        when:
        urlStore.getLongUrl(shortUrl)

        then:
        thrown (ShortUrlNotFoundException)
    }

    def "url store can add and retrieve short url + long url"(){
        given:
        urlStore.putUrl(shortUrl, longUrl)

        when:
        def result = urlStore.getLongUrl(shortUrl)

        then:
        result == longUrl
    }
}
