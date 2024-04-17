package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Vacanсy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VacancyRepository extends JpaRepository <Vacanсy, UUID> {
    void deleteVacanсyByVacancyId (UUID vacancyId);
}
