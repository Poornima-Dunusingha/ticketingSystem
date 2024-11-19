package com.example.ticketingsytem.threads;

import com.example.ticketingsytem.models.Ticket;
import com.example.ticketingsytem.models.Vendor;
import com.example.ticketingsytem.service.TicketPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class VendorThread implements Runnable{

    private final Vendor vendor;
    private final TicketPool ticketPool;
    private static final Logger logger = LoggerFactory.getLogger(VendorThread.class);

    public VendorThread(Vendor vendor, TicketPool ticketPool){
        this.vendor = vendor;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run(){
        //ticket creation by the vendor
        for(int i =1; i<=5; i++){
            try {
            String ticketName = "Ticket- " + vendor.getVendorId() + "-"  + i;

            //Generate a random price
            long price = ThreadLocalRandom.current().nextLong(50, 500);

            //Add tickets to the pool
            ticketPool.addTicket(ticketName, vendor.getVendorId(), price);

            Thread.sleep(100);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted while vendor {} was adding tickets.", vendor.getVendorId(), e);
            } catch (Exception e) {
                logger.error("Error occurred while vendor {} was adding tickets.", vendor.getVendorId(), e);
            }
            }
        }
}
