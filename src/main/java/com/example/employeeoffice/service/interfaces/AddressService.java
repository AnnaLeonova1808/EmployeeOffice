package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Address;

import java.util.UUID;

public interface AddressService {
    Address showAddressById (UUID addressId);
}
