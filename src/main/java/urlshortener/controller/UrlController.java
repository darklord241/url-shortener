package urlshortener.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import urlshortener.service.UrlService;
import urlshortener.dto.UrlRequest;
import urlshortener.dto.UrlResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(path="api/v1/url")
public class UrlController implements UrlApi{

    private final UrlService urlService;

    @Autowired
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping 
    public List<UrlResponse> display() {
        return urlService.displayOutput();
    }

    @GetMapping(path = "{shortCode}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {
        String originalUrl = urlService.getOriginalUrlAndIncrementCount(shortCode);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    @PostMapping
    public ResponseEntity<UrlResponse> addNewUrl(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = urlService.addNew(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
