package com.example.productService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
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
}
