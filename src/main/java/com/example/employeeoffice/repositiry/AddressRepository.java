package com.example.employeeoffice.repositiry;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByAddressId(UUID addressId);
}
