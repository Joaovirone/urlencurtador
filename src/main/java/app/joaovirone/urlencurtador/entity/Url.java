package app.joaovirone.urlencurtador.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
public class Url {
    
    @Id
    private String id;

    private String FullUrl;

    @Indexed(expireAfterSeconds = 0)
    private LocalDateTime expiresAt;
}
