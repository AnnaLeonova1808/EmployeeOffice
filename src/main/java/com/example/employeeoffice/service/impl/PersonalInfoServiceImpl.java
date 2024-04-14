package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.PersonalInfoNotExistException;
import com.example.employeeoffice.repositiry.PersonalInfoRepository;
import com.example.employeeoffice.service.interf.PersonalInfoService;
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
        return personalInfoRepository.getPersonalInfoByPersInfoId(persInfoId);
    }
}



