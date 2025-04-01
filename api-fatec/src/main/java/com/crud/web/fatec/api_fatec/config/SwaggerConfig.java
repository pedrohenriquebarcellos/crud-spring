package com.crud.web.fatec.api_fatec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    //http://localhost:8080/swagger-ui/index.html
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fatec API")
                        .version("1.0")
                        .description("API for Fatec CRUD operations"));
    }
}
