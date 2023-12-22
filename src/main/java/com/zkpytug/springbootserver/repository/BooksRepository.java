package com.zkpytug.springbootserver.repository;

import com.zkpytug.springbootserver.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class BooksRepository {

    @Autowired
    private RedisTemplate<Long, Book> redisTemplate;

    public void save(Book book) {
        redisTemplate.opsForValue()
            .set(book.getId(), book);
    }

    public Book findById(Long id) {
        return redisTemplate.opsForValue()
            .get(id);
    }

}

