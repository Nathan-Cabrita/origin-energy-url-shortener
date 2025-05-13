package com.nathancabrita.origin.energy.url.shortener.application.validator;

import java.net.URI;
import java.util.Optional;

public interface UrlValidator {
    Optional<URI> validateUrl(String url);
}
