package com.example.employeeoffice.controller;


import com.example.employeeoffice.annotation.GetPersonalInfo;
import com.example.employeeoffice.annotation.UpdatePersonalInfo;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/personal_info")
@RequiredArgsConstructor
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @GetPersonalInfo(path = "/getPersInfo/{persInfoId}")
    public PersonalInfo getPersonalInfoById(@PathVariable(name = "persInfoId") UUID persInfoId) {
        return personalInfoService.getPersonalInfoById(persInfoId);
    }

    @UpdatePersonalInfo(path = "/update_persInfo/{persInfoId}")
    public PersonalInfo updatePersonalInfoById(@PathVariable UUID persInfoId, @RequestBody PersonalInfo personalInfo) {
        return personalInfoService.updatePersonalInfoById(persInfoId, personalInfo);
    }

}
