package com.example.ticketingsytem;

import com.example.ticketingsytem.models.Customer;
import com.example.ticketingsytem.service.TicketPool;
import com.example.ticketingsytem.threads.CustomerThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class TicketingSytemApplication {

    public static void main(String[] args) {

        SpringApplication.run(TicketingSytemApplication.class, args);

        // Simulate customers and vendors using thread pool
        ExecutorService executorService = Executors.newFixedThreadPool(10); // Adjust the pool size based on load

        // Assuming you have instances of customer and ticketPool ready
        Customer customer1 = new Customer(); // Populate customer details
        TicketPool ticketPool = new TicketPool(); // Initialize your ticket pool

        // Create customer and vendor threads
        executorService.submit(new CustomerThread(customer1, ticketPool, 5));
        // Submit other threads similarly for vendors

        executorService.shutdown(); // Properly shutdown the executor
    }

}
