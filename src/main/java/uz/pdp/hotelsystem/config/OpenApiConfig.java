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
                .addSecurityItem(new SecurityRequirement().addList("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25zIiwiaWF0IjoxNzQwMTQ4MTgyLCJleHAiOjE3NDAyMzQ1ODJ9.T2BX_isSQamHRJ_aeZfHgcgCxWcCX92TKUY6muZROhDtzxnLCPpHCtqu_o-VoiOYAOyiAGgqqeAsCOZKWUHl7Q"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25zIiwiaWF0IjoxNzQwMTQ4MTgyLCJleHAiOjE3NDAyMzQ1ODJ9.T2BX_isSQamHRJ_aeZfHgcgCxWcCX92TKUY6muZROhDtzxnLCPpHCtqu_o-VoiOYAOyiAGgqqeAsCOZKWUHl7Q", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }


//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI().info(new Info().title("My Api").version("1.0"))
//                .addSecurityItem(new SecurityRequirement().addList("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25zIiwiaWF0IjoxNzQwMTQ4MTgyLCJleHAiOjE3NDAyMzQ1ODJ9.T2BX_isSQamHRJ_aeZfHgcgCxWcCX92TKUY6muZROhDtzxnLCPpHCtqu_o-VoiOYAOyiAGgqqeAsCOZKWUHl7Q"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb25zIiwiaWF0IjoxNzQwMTQ4MTgyLCJleHAiOjE3NDAyMzQ1ODJ9.T2BX_isSQamHRJ_aeZfHgcgCxWcCX92TKUY6muZROhDtzxnLCPpHCtqu_o-VoiOYAOyiAGgqqeAsCOZKWUHl7Q",new SecurityScheme())
//                        .)
//    }
}
// .info(new Info().title("My API").version("1.0"))
//        .addSecurityItem(new SecurityRequirement().addList("Bearer Token"))
//        .components(new io.swagger.v3.oas.models.Components()
//                .addSecuritySchemes("Bearer Token", new SecurityScheme()
//                    .type(SecurityScheme.Type.HTTP)
//                    .scheme("bearer")
//                    .bearerFormat("JWT")
//                )
//                        );