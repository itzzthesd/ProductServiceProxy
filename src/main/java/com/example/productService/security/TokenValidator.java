package com.example.productService.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenValidator {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

   
    public Optional<JwtObject> validateToken(String token){
        RestTemplate restTemplate = restTemplateBuilder.build();
        // call the auth service to get the token and return JWtObject
        return Optional.empty();
    }
}
