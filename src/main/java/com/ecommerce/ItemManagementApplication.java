package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Application Entry Point
 * 
 * This is the main class that bootstraps the Spring Boot application.
 * It initializes the Spring context and starts the embedded web server.
 * 
 * @author Sanjana Kuhite
 * @version 1.0.0
 */
@SpringBootApplication
public class ItemManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemManagementApplication.class, args);
    }
}
