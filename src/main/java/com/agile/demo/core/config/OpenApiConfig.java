package com.agile.demo.core.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenApi configuration
 */
@OpenAPIDefinition(
        servers = {
                @Server(url = "/", description = "default")
        },
        info = @Info(title = "API 명세서",
                description = "API",
                version = "v1",
                contact = @Contact(name = "이성호", email = "cylsh3452@naver.com"),
                license = @License(name = "Apache 2.0",
                url = "https://www.apache.org/licenses/LICENSE-2.0.html")
        )
)

@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@Configuration
public class OpenApiConfig {

    /**
     * customGameOpenApi.
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String[] paths = {"/api/**"};

        return GroupedOpenApi.builder()
                .group("message backend API")
                .pathsToMatch(paths)
                .build();
    }
}
