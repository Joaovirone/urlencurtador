package app.joaovirone.urlencurtador.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import app.joaovirone.urlencurtador.entity.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {
    
}
