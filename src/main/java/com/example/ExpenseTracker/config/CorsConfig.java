package com.example.ExpenseTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.*;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration config = new CorsConfiguration();

        // ✅ MUST be explicit (NO *)
        config.setAllowedOrigins(
                List.of("http://localhost:5500")
        );

        // ✅ Allow common methods
        config.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
        );

        // ✅ Allow all headers (Authorization included)
        config.setAllowedHeaders(List.of("*"));

        // ✅ REQUIRED for JWT / OAuth
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", config);
        return source;
    }
}

