package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAddress;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.service.interfaces.AddressService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
/**
 * REST controller for managing addresses.
 */
@Validated
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    /**
     * Retrieves an address by its unique identifier.
     *
     * @param addressId The unique identifier of the address.
     * @return The address with the specified ID.
     */
    @GetAddress(path = "/show_address/{addressId}")
    public Address showAddressById(@PathVariable(name = "addressId") @UuidFormatChecker String addressId) {

        return addressService.showAddressById(UUID.fromString(addressId));

    }
}
