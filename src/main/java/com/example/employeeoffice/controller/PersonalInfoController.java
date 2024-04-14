package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.service.interf.PersonalInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/personal_info")
@RequiredArgsConstructor
public class PersonalInfoController {
    private final PersonalInfoService personalInfoService;

    @GetMapping("/getPersInfo/{persInfoId}")
    public PersonalInfo getPersonalInfoById(@PathVariable(name = "persInfoId") UUID persInfoId) {
        return personalInfoService.getPersonalInfoById(persInfoId);
    }
}
