package com.example.employeeoffice.service.interf;

import com.example.employeeoffice.entity.PersonalInfo;

import java.util.UUID;

public interface PersonalInfoService {
    PersonalInfo getPersonalInfoById(UUID persInfoId);
}
