package com.zkpytug.springbootserver.repository;

import java.util.List;

import com.zkpytug.springbootserver.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}