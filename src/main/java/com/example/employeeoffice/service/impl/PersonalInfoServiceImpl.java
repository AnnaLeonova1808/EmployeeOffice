package com.example.employeeoffice.service.impl;


import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.exception.*;

import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;

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
}


