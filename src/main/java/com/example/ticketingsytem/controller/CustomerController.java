package com.example.ticketingsytem.controller;

import com.example.ticketingsytem.Repositories.CustomerRepository;
import com.example.ticketingsytem.models.Customer;
import com.example.ticketingsytem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //Adding a new Customer
    @PostMapping("customer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    //Retrieve all the customers
    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    //Get a cutomer by the customerId
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable Long customerId){
        return customerService.getCustomerById(customerId);
    }

    //Delete a cutomer using the cutomerId
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        customerService.deleteCustomer(customerId);
    }


}
