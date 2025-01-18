package com.rbnb.rbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow frontend origin
        config.addAllowedOrigin("http://localhost:4200");

        // Allow all HTTP methods
        config.addAllowedMethod("*");

        // Allow specific headers including authorization
        config.addAllowedHeader("*");
        config.addExposedHeader("Authorization");
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Credentials");

        // Allow credentials
        config.setAllowCredentials(true);

        // Apply configuration to all authenticated endpoints
        source.registerCorsConfiguration("/api/v1/auth/**", config);
        source.registerCorsConfiguration("/api/v1/**", config);

        return new CorsFilter(source);
    }
}

