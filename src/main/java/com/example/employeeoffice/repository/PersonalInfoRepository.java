package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.enums.RolesName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PersonalInfoRepository extends JpaRepository <PersonalInfo, UUID> {
   PersonalInfo getPersonalInfoByPersInfoId (UUID persInfoId);
   boolean existsByEmail(String email);

   List<PersonalInfo> findPersonalInfoByRoles_RoleName(RolesName roleName);

}
