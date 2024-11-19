package com.example.ticketingsytem.threads;


import com.example.ticketingsytem.models.Customer;
import com.example.ticketingsytem.service.CustomerService;
import com.example.ticketingsytem.service.TicketPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomerThread implements Runnable{

    private final Customer customer;
    private final TicketPool ticketPool;
    private final int ticketsToBuy;
    private static final Logger logger = LoggerFactory.getLogger(CustomerThread.class);

    public CustomerThread(Customer customer, TicketPool ticketPool, int ticketsToBuy){
        this.customer = customer;
        this.ticketPool = ticketPool;
        this.ticketsToBuy = ticketsToBuy;
    }

    @Override
    public void run(){
        for (int i = 0; i < ticketsToBuy; i++) {
            try {
            // Simulate customer buying a ticket
            ticketPool.buyTicket(customer.getCustomerId());
            Thread.sleep(150);  // Simulate delay in buying tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted while customer {} was buying tickets.", customer.getCustomerId(), e);
            } catch (Exception e){
                logger.error("Error occurred while customer {} was buying tickets.", customer.getCustomerId(), e);
            }
        }
    }
}
