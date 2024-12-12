package com.example.productService.util;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class RandomTest {

    @Test
     void test1(){
        Random r = new Random();
        int a = r.nextInt(10);
        assert(a<5);
    }
    
}
