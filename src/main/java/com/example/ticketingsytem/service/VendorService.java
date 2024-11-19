package com.example.ticketingsytem.service;

import com.example.ticketingsytem.Repositories.VendorRepository;
import com.example.ticketingsytem.models.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;


    //Adding a new vendor
    public Vendor addVendor(Vendor vendor){
        return vendorRepository.save(vendor);
    }

    //Find individual vendor by the vendor Id
    public Vendor getVendorById(Long vendorId){
        return vendorRepository.findById(vendorId)
                .orElseThrow(()-> new RuntimeException("Vendor with the given Id" + + vendorId+ " was not found" ));
    }

    //Retrieve all the vendors
    public List<Vendor> getAllVendors(){
        return vendorRepository.findAll();
    }

    //Delete a vendor using the vendorId
    public void deleteVendor(Long vendorId){
        vendorRepository.deleteById(vendorId);
    }


}
