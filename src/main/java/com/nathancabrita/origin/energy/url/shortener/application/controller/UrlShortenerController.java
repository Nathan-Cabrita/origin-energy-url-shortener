package com.nathancabrita.origin.energy.url.shortener.application.controller;

import com.nathancabrita.origin.energy.url.shortener.application.exception.ShortUrlNotFoundException;
import com.nathancabrita.origin.energy.url.shortener.application.exception.ValidationException;
import com.nathancabrita.origin.energy.url.shortener.application.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UrlShortenerController {
    private final UrlService urlService;

    @GetMapping("/short-url")
    public ResponseEntity<String> getShortUrl(@RequestParam String url) {
        try {
            return ResponseEntity.ok(urlService.shortenAndStoreLongUrl(url));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("Invalid URL entered");
        } catch (Exception e) {
            return buildInternalError();
        }
    }

    @GetMapping("/{shortUrlKey}")
    public ResponseEntity<String> redirectToLongUrl(@PathVariable String shortUrlKey) {
        try {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header(HttpHeaders.LOCATION, urlService.getLongUrl(shortUrlKey))
                    .build();
        } catch (ShortUrlNotFoundException e) {
            return buildNotFoundError();
        } catch (Exception e) {
            return buildInternalError();
        }
    }

    @GetMapping("/{shortUrlKey}/info")
    public ResponseEntity<?> getLongUrlInfo(@PathVariable String shortUrlKey) {
        try {
            return ResponseEntity.ok().body(urlService.getLongUrlInfo(shortUrlKey));
        } catch (ShortUrlNotFoundException e) {
            return buildNotFoundError();
        } catch (Exception e) {
            return buildInternalError();
        }
    }

    private ResponseEntity<String> buildInternalError() {
        return ResponseEntity.internalServerError().body("Internal Server error");
    }

    private ResponseEntity<String> buildNotFoundError() {
        return ResponseEntity.notFound().build();
    }
}
