package app.joaovirone.urlencurtador.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.joaovirone.urlencurtador.entity.UrlEntity;
import app.joaovirone.urlencurtador.repository.UrlRepository;

@RestController
public class RedirectController {

    private final UrlRepository repository;

    public RedirectController(UrlRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String id) {
        Optional<UrlEntity> urlEntity = repository.findById(id);

        if (urlEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (urlEntity.get().getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.GONE).build(); 
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlEntity.get().getFullUrl()));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}