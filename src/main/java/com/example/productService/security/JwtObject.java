package com.example.productService.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtObject {
     String email;
    Long userId;
    Date createdAt;
    Date ExpiryAt;
    List<Role> role = new ArrayList<>();

}
