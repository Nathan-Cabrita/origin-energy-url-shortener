package com.nathancabrita.origin.energy.url.shortener.application.validator;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.Optional;

@Component
public class UrlValidatorImpl implements UrlValidator{

    @Override
    public Optional<URI> validateUrl(String url) {
        try {
            return Optional.of(new java.net.URL(url).toURI());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
