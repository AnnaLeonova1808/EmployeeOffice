package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Vacanсy;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.exception.VacancyNotFoundExeption;
import com.example.employeeoffice.repository.VacancyRepository;
import com.example.employeeoffice.service.interfaces.VacancyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;

    @Override
    @Transactional
    public void deleteVacancyById(UUID vacancyId) {
        Vacanсy vacancy = vacancyRepository.findById(vacancyId).orElse(null);
        if (vacancy == null) {
            throw new VacancyNotFoundExeption(ErrorMessage.VACANCY_NOT_EXIST);
        }
        vacancyRepository.deleteVacanсyByVacancyId(vacancyId);
    }
}
