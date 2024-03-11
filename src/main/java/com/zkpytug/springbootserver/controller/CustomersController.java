package com.zkpytug.springbootserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zkpytug.springbootserver.repository.CustomerRepository;
import com.zkpytug.springbootserver.entity.Customer;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/customers")
public class CustomersController {
    private static final Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/create")
    public String newBook(@RequestBody Customer newCustomer, HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        logger.info("X-Forwarded-For: {}", xForwardedFor);
        repository.save(newCustomer);
        return "Added";
    }

    @GetMapping("/get/{id}")
    public String findOne(@PathVariable Long id, HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        logger.info("X-Forwarded-For: {}", xForwardedFor);
        return repository.findById(id).toString();
    }
}
