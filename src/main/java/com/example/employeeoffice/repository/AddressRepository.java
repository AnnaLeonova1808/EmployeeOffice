package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByAddressId(UUID addressId);
    List<Address> findByPersonalInfoPersInfoId(UUID persInfoId);
}
