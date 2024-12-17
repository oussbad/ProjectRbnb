package com.rbnb.rbnb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disables CSRF protection (optional)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users/register").permitAll()  // Allow unauthenticated access to /register
                        .anyRequest().authenticated())  // Secure all other endpoints
                .formLogin(form -> form.disable()) // Disable form login if you're using token-based auth
                .httpBasic(httpBasic -> httpBasic.disable()); // Disable basic authentication if not needed

        return http.build();
    }
}
