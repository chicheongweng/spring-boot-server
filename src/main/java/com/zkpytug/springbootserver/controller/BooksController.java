package com.zkpytug.springbootserver.controller;

import com.zkpytug.springbootserver.entity.Book;
import com.zkpytug.springbootserver.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/books")
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);

    @Autowired
    private BooksRepository repository;

    @PostMapping("/create")
    public String newBook(@RequestBody Book newBook, HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        logger.info("X-Forwarded-For: {}", xForwardedFor);
        repository.save(newBook);
        return "Added";
    }

    @GetMapping("/get/{id}")
    public String findOne(@PathVariable Long id, HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        logger.info("X-Forwarded-For: {}", xForwardedFor);
        return repository.findById(id).toString();
    }

}
