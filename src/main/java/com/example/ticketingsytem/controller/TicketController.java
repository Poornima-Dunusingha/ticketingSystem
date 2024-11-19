package com.example.ticketingsytem.controller;


import com.example.ticketingsytem.models.Customer;
import com.example.ticketingsytem.models.Ticket;
import com.example.ticketingsytem.models.Vendor;
import com.example.ticketingsytem.service.CustomerService;
import com.example.ticketingsytem.service.TicketPool;
import com.example.ticketingsytem.service.VendorService;
import com.example.ticketingsytem.threads.CustomerThread;
import com.example.ticketingsytem.threads.VendorThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketPool ticketPool;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/start")
    public String startTicketPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(10); // A pool of 10 threads

        //Simulates to 5 vendors who are adding tickets simultaneously
        for (int i = 1; i <= 5; i++) {
                Vendor vendor = vendorService.getVendorById((long) i);
                executorService.submit(new VendorThread(vendor, ticketPool));
        }

        // Simulate 5 customers buying tickets concurrently
        for (int i = 1; i <= 5; i++) {
            Customer customer = customerService.getCustomerById((long) i);
            int ticketsToBuy = (int) (Math.random() * 5) + 1;
            executorService.submit(new CustomerThread(customer, ticketPool, 3));  // CustomerThread task to buy 3 tickets each
        }


        executorService.shutdown(); // Gracefully shut down after tasks finish
        return "Simulation started. Vendors and customers are interacting with the ticket pool.";
    }



    // Retrieve all available tickets
    @GetMapping("/available")
    public List<Ticket> getAvailableTickets() {
        return ticketPool.getAllTickets(); // Modify `TicketPool` to return only unsold tickets if needed
    }



}
