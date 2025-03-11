package com.code_red.phc_attendance_system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // ✅ Allowed Origins (React, Flutter, Android, etc.)
        config.setAllowedOrigins(Arrays.asList(
            "http://localhost:5173",  // Flutter Web
            "http://10.0.2.2:3000",   // Flutter Emulator
            "http://localhost:3000"  // React App
          
        ));

        // ✅ Allowed Methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // ✅ Allowed Headers
        config.setAllowedHeaders(Arrays.asList("*"));

        // ✅ Allow sending Cookies/JWT Tokens
        config.setAllowCredentials(true);

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
