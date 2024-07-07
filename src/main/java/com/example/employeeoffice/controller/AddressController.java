package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAddress;
import com.example.employeeoffice.annotation.GetAddressesByPersonalInfoId;
import com.example.employeeoffice.annotation.UpdateAddress;
import com.example.employeeoffice.annotation.UpdatePersonalInfo;
import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.service.interfaces.AddressService;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    private final PersonalInfoService personalInfoService;

    /**
     * Retrieves an address by its unique identifier.
     *
     * @param addressId The unique identifier of the address.
     * @return The address with the specified ID.
     */
    //http://localhost:8080/address/show_address/b23a92eb-398c-4ba9-9680-b4b3a72a910d
    @GetAddress(path = "/show_address/{addressId}")
    public Address showAddressById(@PathVariable(name = "addressId") @UuidFormatChecker String addressId) {

        return addressService.showAddressById(UUID.fromString(addressId));
    }

    /**
     * Updates an address by its unique identifier.
     *
     * @param addressId The unique identifier of the address.
     * @param address The updated address details.
     * @return The updated address with the specified ID.
     */
    @UpdateAddress(path = "/update_address/{addressId}")
    public Address updateAddressById(@PathVariable(name = "addressId") @UuidFormatChecker String addressId,
                                               @RequestBody @Valid Address address) {

        return addressService.updateAddressById(UUID.fromString(addressId), address);
    }

    /**
     * Finds addresses by personal information ID.
     *
     * @param persInfoId The unique identifier of the personal information.
     * @return A list of addresses associated with the specified personal information ID.
     */
    @GetAddressesByPersonalInfoId(path = "/find_by_pers_info_id/{persInfoId}")
    public ResponseEntity<List<Address>> findByPersInfoId(@PathVariable(name = "persInfoId") @UuidFormatChecker String persInfoId) {
        List<Address> addresses = addressService.findAllAddressesByPersInfoId(UUID.fromString(persInfoId));
        return ResponseEntity.ok(addresses);
    }
}

