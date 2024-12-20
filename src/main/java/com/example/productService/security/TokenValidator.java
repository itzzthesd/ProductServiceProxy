package com.example.productService.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidator { // if you dont want to use the spring securoty and Oauth then we can 
    // write our own logic to implement the authentication and authorisation

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

   
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // call the auth service to get the token and return JWtObject
        return Optional.empty();
    }
}
