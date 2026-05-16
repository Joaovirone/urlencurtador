package app.joaovirone.urlencurtador.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;



@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info
        (title = "URL Encurtador API",
         version = "1.0",
         description = "API para encurtar URLs e redirecionar para as URLs originais.")
)
public class SwaggerConfig {
    
}
