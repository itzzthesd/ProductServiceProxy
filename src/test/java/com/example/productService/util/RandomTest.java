package com.example.productService.util;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;
import static org.hamcrest.Matchers.not;
import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class RandomTest {

    @Test
     void test1(){
        Random r = new Random();
        int a = r.nextInt(10);
        assert(a<5);
    }

    @Test
    public void given2Strings_whenIsNotEqualRegardlessWhiteSpace_thenCorrect() {
        String str1 = "text";
        String str2 = " texts ";
        Character c = 'a';
        assertThat(c)
        .isNotEqualTo('a')
        .inUnicode()
        .isGreaterThanOrEqualTo('b')
        .isLowerCase();
    }
    
}
