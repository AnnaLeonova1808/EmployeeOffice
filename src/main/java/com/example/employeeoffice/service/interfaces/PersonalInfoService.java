package com.example.employeeoffice.service.interfaces;


import com.example.employeeoffice.entity.PersonalInfo;

import java.util.UUID;

public interface PersonalInfoService {
    PersonalInfo getPersonalInfoById(UUID persInfoId);

    PersonalInfo updatePersonalInfoById(UUID persInfoId, PersonalInfo personalInfo);
}

