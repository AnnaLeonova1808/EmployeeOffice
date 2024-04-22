package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.dto.PersonalInfoAfterCreationDto;
import com.example.employeeoffice.dto.PersonalInfoCreateDto;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.generator.PasswordGenerator;
import com.example.employeeoffice.mapper.PersonalInfoMapper;
import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import com.example.employeeoffice.utils.PasswordHashing;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper personalInfoMapper;
    private final RoleRepository roleRepository;

    @Override
    public PersonalInfo getPersonalInfoById(UUID persInfoId) {

        PersonalInfo personalInfo = personalInfoRepository.getPersonalInfoByPersInfoId(persInfoId);
        if (personalInfo == null) {
            throw new PersonalInfoNotExistException(ErrorMessage.PERSONAL_INFO_NOT_EXIST);
        }
        return personalInfo;
    }

    @Override
    @Transactional
    public PersonalInfo updatePersonalInfoById(UUID persInfoId, PersonalInfo personalInfo) {
        PersonalInfo updatePersonalInfo = personalInfoRepository.getPersonalInfoByPersInfoId(persInfoId);
        if (updatePersonalInfo == null) {
            throw new PersonalInfoNotExistException(ErrorMessage.PERSONAL_INFO_NOT_EXIST);
        }

        updatePersonalInfo.setBirthday(personalInfo.getBirthday());
        updatePersonalInfo.setPhoneNumber(personalInfo.getPhoneNumber());
        updatePersonalInfo.setEmail(personalInfo.getEmail());
        updatePersonalInfo.setPassword(personalInfo.getPassword());
        updatePersonalInfo.setSalary(personalInfo.getSalary());

        return updatePersonalInfo;
        }

    @Override
    public PersonalInfoAfterCreationDto createPersonalInfo(PersonalInfoCreateDto personalInfoCreateDto) {
        PersonalInfo personalInfo= personalInfoRepository.findPersonalInfoByEmail(personalInfoCreateDto.getEmail());
        if(personalInfo!= null){
            throw new PersonalInfoAlreadyExistsException(ErrorMessage.PERSONAL_INFO_ALREADY_EXIST);
        }

        PersonalInfo entity = personalInfoMapper.toEntity(personalInfoCreateDto);

        if (entity.getPassword() == null || entity.getPassword().isEmpty()) {
            entity.setPassword(PasswordGenerator.generatePasswordBasedOnUUID());
        } else {
            String hashedPassword = PasswordHashing.hashPassword(entity.getPassword());
            entity.setPassword(hashedPassword);
        }

        Role userRole = new Role();
        userRole.setRoleName(RolesName.USER);
        userRole = roleRepository.save(userRole);

        entity.setRoles(Collections.singleton(userRole));

        PersonalInfo personalInfoAfterCreation= personalInfoRepository.save(entity);
        return personalInfoMapper.toDto(personalInfoAfterCreation);
    }
}


