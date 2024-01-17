package com.zkpytug.springbootserver;

import com.azure.core.exception.ClientAuthenticationException;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;
import com.zkpytug.springbootserver.entity.Book;
import com.zkpytug.springbootserver.entity.Customer;
import com.zkpytug.springbootserver.repository.BooksRepository;
import com.zkpytug.springbootserver.repository.CustomerRepository;
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
    public CommandLineRunner demo(CustomerRepository repository, BooksRepository book, SecretClient secretClient) {
        return (args) -> {
            // Create a secret client using the DefaultAzureCredential
            /*
                        SecretClient client = new SecretClientBuilder()
                                .vaultUrl("https://kv-rcit-rhk-ucp-dev-01.vault.azure.net/")
                                .credential(new DefaultAzureCredentialBuilder().build())
                                .buildClient();

                        try {
                            log.info("secret redis-rcit-rhk-ucp-dev-01-accesskey: " + client.getSecret("redis-rcit-rhk-ucp-dev-01-accesskey").getValue());
                            log.info("secret sqldb-rcit-rhk-ucp-dev-01-password: " + client.getSecret("sqldb-rcit-rhk-ucp-dev-01-password").getValue());
                        } catch (ClientAuthenticationException e) {
                            //Handle Exception
                            e.printStackTrace();
                        }
             */
            log.info("Secret redis-rcit-rhk-ucp-dev-01-accesskey: " + secretClient.getSecret("redis-rcit-rhk-ucp-dev-01-accesskey").getValue());
            log.info("Secret sqldb-rcit-rhk-ucp-dev-01-password: " + secretClient.getSecret("sqldb-rcit-rhk-ucp-dev-01-password").getValue());
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

            repository.save(new Customer(1L, "Jack", "Bauer"));
            repository.save(new Customer(2L, "Chloe", "O'Brian"));
            repository.save(new Customer(3L, "Kim", "Bauer"));
            repository.save(new Customer(4L, "David", "Palmer"));
            repository.save(new Customer(5L, "Michelle", "Dessler"));

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