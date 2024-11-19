package com.example.ticketingsytem.service;

import com.example.ticketingsytem.Repositories.TicketRepository;
import com.example.ticketingsytem.models.Customer;
import com.example.ticketingsytem.models.Ticket;
import com.example.ticketingsytem.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TicketPool {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private VendorService vendorService;

    @Autowired
    private CustomerService customerService;

    private final ConcurrentLinkedQueue<Ticket> ticketQueue = new ConcurrentLinkedQueue<>();

    //Adding a new ticket to the ticket pool and database
    public synchronized Ticket addTicket(String ticketName, Long vendorId, Long price){

        //Checking if the Vendor is valid
        Vendor vendor = vendorService.getVendorById(vendorId);

        //Adding a new ticket
        Ticket ticket = new Ticket();
        ticket.setTicketName(ticketName);
        ticket.setVendorId(vendor.getVendorId());
        ticket.setPrice(price);

        //Adding the queue
        ticketQueue.add(ticket);
        System.out.println("Ticket added to pool: " + ticketName);

        //Add the ticket to the database
        return ticketRepository.save(ticket);
    }

    // Retrieve a ticket for a customer (simulated purchase)
    public Ticket buyTicket(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);

        Ticket ticket = ticketQueue.poll(); // Get the next available ticket
        if (ticket == null) {
            System.out.println("No tickets available for purchase.");
            return null;
        }

        // Assign ticket to customer
        ticket.setCustomerId(customer.getCustomerId());

        // Update the ticket in the database
        ticketRepository.save(ticket);
        System.out.println("Ticket purchased: " + ticket.getTicketName() + " by Customer ID: " + customerId);

        return ticket;
    }


    //Retrieving a ticket by ticket Id
    public Ticket getTicketById(Long ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket with the given id" + ticketId + " was not found"));
    }

    //Get all the tickets
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }

    //Delete a ticket
    public void deleteTicket(Long ticketId){
        ticketRepository.deleteById(ticketId);
    }

//    //Retrieve a ticket using a customer
//    public List<Ticket> getTicketByCustomer(Long customerId){
//        return ticketRepository.findByCustomerId(customerId);
//    }

    // Get the current number of tickets in the pool
    public int getTicketCount() {
        return ticketQueue.size();
    }





}
