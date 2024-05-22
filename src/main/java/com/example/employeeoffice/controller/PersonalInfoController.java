package com.example.employeeoffice.controller;


import com.example.employeeoffice.annotation.GetPersonalInfo;
import com.example.employeeoffice.annotation.GetPersonalInfoByRoleName;
import com.example.employeeoffice.annotation.UpdatePersonalInfo;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequestMapping("/personal_info")
@RequiredArgsConstructor
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @GetPersonalInfo(path = "/getPersInfo/{persInfoId}")
    public PersonalInfo getPersonalInfoById(@PathVariable(name = "persInfoId") @UuidFormatChecker String persInfoId) {
        return personalInfoService.getPersonalInfoById(UUID.fromString(persInfoId));
    }

    @UpdatePersonalInfo(path = "/update_persInfo/{persInfoId}")
    public PersonalInfo updatePersonalInfoById(@PathVariable(name = "persInfoId") @UuidFormatChecker String persInfoId,
                                               @RequestBody @Valid PersonalInfo personalInfo) {
        return personalInfoService.updatePersonalInfoById(UUID.fromString(persInfoId), personalInfo);
    }

    @GetPersonalInfoByRoleName(path = "/roles/{roleName}")
    public List<PersonalInfo> showAllPersonalInfoByRoleName(@PathVariable(name = "roleName") String roleName){
        return personalInfoService.showAllPersonalInfoByRoleName(roleName);
    }



}
