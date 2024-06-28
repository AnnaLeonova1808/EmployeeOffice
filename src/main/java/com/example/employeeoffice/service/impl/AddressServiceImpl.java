package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Address;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.enums.AddressType;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.repository.AddressRepository;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final PersonalInfoRepository personalInfoRepository;

@Override
    public Address showAddressById(UUID addressId) {

        Address address = addressRepository.findByAddressId(addressId);
        if (address == null) {
            throw new AddressNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND);
        }
        return address;
    }
    @Override
    @Transactional
    public Address updateAddressById(UUID addressId, Address address) {
        Address updateAddress = addressRepository.findByAddressId(addressId);
        if (updateAddress== null) {
            throw new AddressNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND);
        }
        updateAddress.setAddressType(address.getAddressType());
        updateAddress.setApartNumber(address.getApartNumber());
        updateAddress.setHouseNumber(address.getHouseNumber());
        updateAddress.setStreet(address.getStreet());
        updateAddress.setCity(address.getCity());

        return addressRepository.saveAndFlush(updateAddress);
    }
    @Override
    public List<Address> findAllAddressesByPersInfoId(UUID persInfoId) {
        Optional<PersonalInfo> personalInfoOptional = Optional.ofNullable(personalInfoRepository.getPersonalInfoByPersInfoId(persInfoId));
        if (personalInfoOptional.isEmpty()) {
            throw new PersonalInfoNotFoundException(ErrorMessage.PERSONAL_INFO_NOT_FOUND);
        }
        return addressRepository.findByPersonalInfoPersInfoId(persInfoId);
    }

}
