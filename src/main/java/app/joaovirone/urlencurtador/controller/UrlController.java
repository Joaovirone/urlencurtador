package app.joaovirone.urlencurtador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.joaovirone.urlencurtador.dto.UrlEncurtadaRequestDto;


@RestController
@RequestMapping("/api/v1/URL_Encurtador")
public class UrlController {
    

    @PostMapping("/encurtar-url")
    public ResponseEntity<Void> shortUrl(@RequestBody  UrlEncurtadaRequestDto String) {
        
        
        return entity;
    }
    
}
