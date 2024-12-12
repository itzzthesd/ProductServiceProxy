package com.example.productService.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.productService.utils.Calculator;

public class CalculatorTest {

    @Test
    @DisplayName("Testing 3 + 5 = 8")
    public void Test_add(){
        Calculator calculator = new Calculator();
        int c = calculator.add(3,5);
        assertEquals(c, 8);
    }

    @Test
    public void Test_div_byZero(){
        Calculator calculator = new Calculator();
        assertThrows(ArithmeticException.class, () -> calculator.div(1,0));
    }
}
