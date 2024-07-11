package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;

import java.util.Set;
import java.util.UUID;

public interface VacancyService {
    String deleteVacancyById(UUID vacancyId);
    VacancyAfterCreationDto createVacancy(VacancyCreateDto vacancyCreateDto);
    Set<String> showAllVacancies();
}
