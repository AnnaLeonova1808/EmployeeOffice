package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VacancyRepository extends JpaRepository <Vacancy, UUID> {
    void deleteVacancyByVacancyId(UUID vacancyId);
    Vacancy findByVacancyDescription(String vacancyDescription);
}
