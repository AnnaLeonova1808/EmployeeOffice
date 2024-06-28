package com.example.employeeoffice.service.impl;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.enums.RolesName;
import com.example.employeeoffice.exception.*;

import com.example.employeeoffice.repository.PersonalInfoRepository;
import com.example.employeeoffice.repository.RoleRepository;
import com.example.employeeoffice.service.interfaces.PersonalInfoService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private final PersonalInfoRepository personalInfoRepository;
    private final RoleRepository roleRepository;

    @Override
    public PersonalInfo getPersonalInfoById(UUID persInfoId) {

        PersonalInfo personalInfo = personalInfoRepository.getPersonalInfoByPersInfoId(persInfoId);

        if (personalInfo == null) {
            throw new PersonalInfoNotFoundException(ErrorMessage.PERSONAL_INFO_NOT_FOUND);
        }
        return personalInfo;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
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

        updatePersonalInfo = personalInfoRepository.save(updatePersonalInfo);

        return updatePersonalInfo;
    }

    @Override
    public List<PersonalInfo> showAllPersonalInfoByRoleName(String roleName) {
        try {
            RolesName enumRoleName = RolesName.valueOf(roleName);
            List<PersonalInfo> personalInfoList = personalInfoRepository.findPersonalInfoByRoles_RoleName(enumRoleName);
            if (personalInfoList.isEmpty()) {
                throw new ListOfPersonalInfoIsEmptyException("No personal information found for role: " + roleName);
            }
            return personalInfoList;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name: " + roleName);
        }
    }
    @Transactional
    public PersonalInfo findByUsername(String username) {

        PersonalInfo personalInfo = personalInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        personalInfo.getRoles().forEach(role -> {
            roleRepository.findByRoleName(role.getRoleName()).ifPresent(fetchedRole -> {
                role.setAuthorities(fetchedRole.getAuthorities());
            });
        });

        return personalInfo;
    }
}


