package com.nathancabrita.origin.energy.url.shortener.application.validator;

public class UrlValidatorImpl implements UrlValidator{

    @Override
    public boolean validateUrl(String url) {
        try {
            new java.net.URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
