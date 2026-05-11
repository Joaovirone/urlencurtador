package app.joaovirone.urlencurtador.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
public class UrlEntity {
    
    @Id
    private String id;

    private String fullUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAt;

    public UrlEntity(){

    }

    public UrlEntity (String id, String fullUrl, LocalDateTime expiresAt){
        this.id = id;
        this.fullUrl = fullUrl;
        this.expiresAt = expiresAt;
    }

    public String getId(){
        return id;
    }
    public String getFullUrl(){
        return fullUrl;
    }
    public LocalDateTime getExpiresAt(){
        return expiresAt;
    }
}
