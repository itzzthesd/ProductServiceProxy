package com.example.productService.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    //@Bean
    // public SecurityFilterChain defauSecurityFilterChain(HttpSecurity http) throws Exception{
    //     //http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated());
    //     http.authorizeHttpRequests((authorize) -> authorize
    //                 //authorize.requestMatchers("/products").hasAuthority("admin")
    //                 .anyRequest().permitAll())
    //                 .csrf().disable()
    //                 .formLogin().disable() // Disable form-based login
    //                 .httpBasic().disable();
    //                 //.formLogin(Customizer.withDefaults());
    //     return http.build();
    //}

     /**
     * Authenticate every request
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/*").permitAll()
                        .anyRequest().permitAll()

                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .cors().disable()
                .csrf().disable();
        return http.build();

    }

    @Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("*")); // Adjust for production
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}
}
