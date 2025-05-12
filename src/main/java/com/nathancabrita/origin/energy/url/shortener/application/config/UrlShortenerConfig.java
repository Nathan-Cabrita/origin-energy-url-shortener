package com.nathancabrita.origin.energy.url.shortener.application.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "application.short-url")
public class  UrlShortenerConfig {
    private String domain;
    private int shortUrlKeyLength;
}


