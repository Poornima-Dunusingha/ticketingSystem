package com.example.ticketingsytem.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;
    @Column(name = "ticket_name", nullable = false)
    private String ticketName;
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "price", nullable = false)
    private Long price;

    public Ticket(Long ticketId, String ticketName, Long vendorId, Long customerId, Long price) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.vendorId = vendorId;
        this.customerId = customerId;
        this.price = price;
    }

    public Ticket() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
