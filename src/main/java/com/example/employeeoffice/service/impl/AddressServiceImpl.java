package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.exception.AddressNotExistException;
import com.example.employeeoffice.exception.EmployeeNotExistException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repositiry.AddressRepository;
import com.example.employeeoffice.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

@Override
    public Address showAddressById(UUID addressId) {
        Address address = addressRepository.findByAddressId(addressId);
        if (address == null) {
            throw new AddressNotExistException(ErrorMessage.ADDRESS_NOT_EXIST);

        }

        return addressRepository.findByAddressId(addressId);
    }

}
