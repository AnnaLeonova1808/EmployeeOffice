package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByAddressId(UUID addressId);
}
