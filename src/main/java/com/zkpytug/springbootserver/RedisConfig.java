package com.zkpytug.springbootserver;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.zkpytug.springbootserver.entity.Book;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableConfigurationProperties(RedisProperties.class)
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-${env:local}.properties" })
public class RedisConfig {

    @Bean
    public RedisTemplate<Long, Book> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Long, Book> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // Add some specific configuration here. Key serializers, etc.
        return template;
    }

}
