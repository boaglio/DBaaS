package com.boaglio.dbaas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Config - OpenAPI v3
 * 
 * @author Fernando Boaglio
 *
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("DBaaS API")
                        .description("DBaaS API")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation().description("GitHub").url("https://github.com/boaglio/DBaaS"));
    }

}
