package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Address;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address showAddressById (UUID addressId);
    Address updateAddressById (UUID addressId, Address address);
    List<Address> findAllAddressesByPersInfoId(UUID persInfoId);


}
