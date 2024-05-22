package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAddress;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.service.interfaces.AddressService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
@Validated
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor

public class AddressController {
    private final AddressService addressService;

    @GetAddress(path = "/show_address/{addressId}")
    public Address showAddressById(@PathVariable(name = "addressId") @UuidFormatChecker String addressId) {
        return addressService.showAddressById(UUID.fromString(addressId));
    }
}
