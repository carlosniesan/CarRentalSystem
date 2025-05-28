package carlosniesan.carrentalsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Car Rental System API")
                        .version("1.0")
                        .description("A REST API for a car rental system that allows managing car inventory, calculating rental prices, and tracking customer loyalty points.")
                        .termsOfService("https://example.com/terms")
                        .contact(new Contact()
                                .name("Car Rental Support")
                                .email("support@carrentalsystem.com")
                                .url("https://carrentalsystem.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}