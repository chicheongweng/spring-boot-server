package com.zkpytug.springbootserver;

import com.zkpytug.springbootserver.entity.Book;
import com.zkpytug.springbootserver.entity.Customer;
import com.zkpytug.springbootserver.repository.CustomerRepository;
import com.zkpytug.springbootserver.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootServerApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServerApplication.class);
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository, BooksRepository book) {
        return (args) -> {
            // save a few customers
            book.save(new Book(1L, "Harry Porter"));
            book.save(new Book(2L, "The Old Man and the Sea"));

            Book b = book.findById(1L);
            log.info("Book found with findById(1L):");
            log.info("--------------------------------");
            log.info(b.toString());
            log.info("");

            b = book.findById(2L);
            log.info("Book found with findById(2L):");
            log.info("--------------------------------");
            log.info(b.toString());
            log.info("");

            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
            log.info("");

            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");

            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");
        };
    }

}