package com.example.ticketingsytem.controller;

import com.example.ticketingsytem.models.Vendor;
import com.example.ticketingsytem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    //Creating a new vendor
    @PostMapping("/vendor")
    public Vendor createVendor(@RequestBody Vendor vendor){
        return vendorService.addVendor(vendor);
    }

    //Retrieve a list of all vendors
    @GetMapping("/vendor")
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }

    //Get vendor by Id
    @GetMapping("/{vendorId}")
    public Vendor getVendorById(@PathVariable Long vendorId){
        return vendorService.getVendorById(vendorId);
    }

    //Delete Vendor by Id
    @DeleteMapping("/{vendorId}")
    public void deleteVendor(@PathVariable Long vendorId){
        vendorService.deleteVendor(vendorId);
    }
}
