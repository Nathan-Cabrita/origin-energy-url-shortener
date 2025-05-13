package com.nathancabrita.origin.energy.url.shortener.application.domain;

import lombok.Data;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Data
public class LongUrl {
    private URI originalUrl;
    private ZonedDateTime createdTime;
    private String host;
    private String port;
    private String path;
    private List<String> queryParameters;

    public LongUrl (URI originalUrl){
        this.originalUrl = originalUrl;
        this.createdTime = ZonedDateTime.now();
        this.host = originalUrl.getHost();
        this.path = originalUrl.getPath();
        this.queryParameters = getQueryParameters(originalUrl.getQuery());
    }

    private List<String> getQueryParameters(String query){
        return Arrays.stream(query.split("&")).toList();
    }
}
