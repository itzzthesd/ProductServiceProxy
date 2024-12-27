package com.example.productService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    // @Bean
    // JedisConnectionFactory jedisConnectionFactory() {
    //     return new JedisConnectionFactory();
    // }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
        = new JedisConnectionFactory();
        jedisConFactory.setHostName("127.0.0.1");
        jedisConFactory.setPort(6379);
        return jedisConFactory;
    }

}
