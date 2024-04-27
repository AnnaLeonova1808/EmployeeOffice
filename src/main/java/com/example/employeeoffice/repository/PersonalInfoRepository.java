package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.PersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonalInfoRepository extends JpaRepository <PersonalInfo, UUID> {
   PersonalInfo getPersonalInfoByPersInfoId (UUID persInfoId);

    PersonalInfo findPersonalInfoByEmail(String email);

    boolean existsByEmail(String email);
}
