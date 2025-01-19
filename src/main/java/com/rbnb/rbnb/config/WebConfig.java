package com.rbnb.rbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.Arrays;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow frontend origin
        config.addAllowedOrigin("http://localhost:4200");

        // Allow all HTTP methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Allow all headers
        config.addAllowedHeader("*");

        // Expose necessary response headers
        config.setExposedHeaders(Arrays.asList(
                "Authorization",
                "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials"
        ));

        // Allow credentials (important for sending cookies and auth headers)
        config.setAllowCredentials(true);

        // Configure max age for preflight requests cache
        config.setMaxAge(3600L);

        // Apply CORS configuration to all paths
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/api/v1/auth/**", config);
        source.registerCorsConfiguration("/api/properties/**", config);

        return new CorsFilter(source);
    }
}