package uz.pdp.hotelsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by: Umar
 * DateTime: 2/17/2025 1:11 PM
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("My API").version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4b25kYW1pciIsImlhdCI6MTc0MDQxMDY3OCwiZXhwIjoxNzQwNDk3MDc4fQ.JmTLu8vv9SsHgSla7WT1cQLdb9-fBiLijOzLmsgKpVwsI9nxvBrp1nXaN_A5AUh_cwXgQp4fhHQYFaJ_r2yuJQ"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ4b25kYW1pciIsImlhdCI6MTc0MDQxMDY3OCwiZXhwIjoxNzQwNDk3MDc4fQ.JmTLu8vv9SsHgSla7WT1cQLdb9-fBiLijOzLmsgKpVwsI9nxvBrp1nXaN_A5AUh_cwXgQp4fhHQYFaJ_r2yuJQ", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }


}