package com.example.ticketingsytem.service;

import com.example.ticketingsytem.Repositories.CustomerRepository;
import com.example.ticketingsytem.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    //Add a new Customer
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    //Find a customer by customerId

    public Customer getCustomerById(Long customerId){
        return customerRepository.findById(customerId)
                .orElseThrow(()-> new RuntimeException("Customer with the given id" + customerId + " was not found."));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long customerId){
        customerRepository.deleteById(customerId);
    }
}
