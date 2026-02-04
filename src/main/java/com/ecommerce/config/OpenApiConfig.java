package com.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger Configuration
 * 
 * Configures the OpenAPI documentation for the API.
 * Swagger UI will be available at: /swagger-ui.html
 * API Docs will be available at: /v3/api-docs
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    /**
     * Configures OpenAPI documentation metadata.
     * 
     * @return OpenAPI configuration object
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Item Management API")
                        .version("1.0.0")
                        .description("""
                                RESTful API for managing e-commerce items.

                                ## Features
                                - Add new items to the catalog
                                - Retrieve items by ID
                                - List all items
                                - Input validation with detailed error messages

                                ## Data Storage
                                Items are stored in-memory using an ArrayList.
                                Data is reset when the application restarts.
                                """)
                        .contact(new Contact()
                                .name("Sanjana Kuhite")
                                .email("sanjanakuhite3@gmail.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:" + serverPort)
                                .description("Local Development Server"),
                        new Server()
                                .url("https://item-management-api-9qbm.onrender.com/")
                                .description("Production Server")));
    }
}
