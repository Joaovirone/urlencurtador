package app.joaovirone.urlencurtador.controller;

import java.net.URI;
import java.time.LocalDateTime;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.joaovirone.urlencurtador.dto.UrlEncurtadaRequestDto;
import app.joaovirone.urlencurtador.dto.UrlEncurtadaResponseDto;
import app.joaovirone.urlencurtador.entity.UrlEntity;
import app.joaovirone.urlencurtador.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/v1/URL_Encurtador")
public class UrlController {


    private final UrlRepository repository;

    public UrlController(UrlRepository repository){
        this.repository = repository;
    }

    @PostMapping("/encurtar-url")
    public ResponseEntity<UrlEncurtadaResponseDto> shortUrl(@RequestBody  UrlEncurtadaRequestDto request, HttpServletRequest serveletRequest) {

        String id;

       do { 
           id = RandomStringUtils.randomAlphanumeric(5,10);
       } while (repository.existsById(id));

       repository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));


       var redirectUrl = serveletRequest.getRequestURL().toString().replace("encurtar-url", id);


        return ResponseEntity.ok(new UrlEncurtadaResponseDto(redirectUrl));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {

        var url = repository.findById(id);

        if (url.isEmpty()){

            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();

    }
    
}
