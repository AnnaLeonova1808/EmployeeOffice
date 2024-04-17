package com.example.employeeoffice.repository;

import com.example.employeeoffice.entity.Vacanсy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VacancyRepositoryDto extends JpaRepository <Vacanсy, UUID> {
    Vacanсy findByVacancyDescription(String vacancyDescription);
}
