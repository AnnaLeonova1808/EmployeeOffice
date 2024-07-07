package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Vacancy;

import java.util.List;
import java.util.UUID;

public interface VacancyService {
    String deleteVacancyById(UUID vacancyId);
    VacancyAfterCreationDto createVacancy(VacancyCreateDto vacancyCreateDto);

    List<Vacancy> showAllVacancies();
}
