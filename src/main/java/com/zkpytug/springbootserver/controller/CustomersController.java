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

@RestController
@RequestMapping("/customers")
public class CustomersController {

    @Autowired
    private CustomerRepository repository;

    @PostMapping("/create")
    public String newBook(@RequestBody Customer newCustomer) {
        repository.save(newCustomer);
        return "Added";
    }

    @GetMapping("/get/{id}")
    public String findOne(@PathVariable Long id) {
        return repository.findById(id).toString();
    }
}
