package com.nathancabrita.origin.energy.url.shortener.application.domain;

public record ShortUrl (String domain, String shortUrlKey){
    @Override
    public String toString(){
        return domain + shortUrlKey;
    }
}
