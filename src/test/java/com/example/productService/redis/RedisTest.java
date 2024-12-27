package com.example.productService.redis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedisConnection() {
        // Arrange
        String testKey = "testKey";
        String testValue = "testValue";

        // Act
        redisTemplate.opsForValue().set(testKey, testValue);
        String retrievedValue = redisTemplate.opsForValue().get(testKey);

        // Assert
        assertThat(retrievedValue).isNotNull();
        assertThat(retrievedValue).isEqualTo(testValue);

        System.out.println("Redis connection test passed. Retrieved value: " + retrievedValue);
    }
}
