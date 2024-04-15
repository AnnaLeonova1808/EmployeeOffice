package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor

public class AddressController {
    private final AddressService addressService;

    @GetMapping("/show_address/{addressId}")
    public Address showAddressById(@PathVariable(name = "addressId") UUID addressId) {
        return addressService.showAddressById(addressId);
    }
}
