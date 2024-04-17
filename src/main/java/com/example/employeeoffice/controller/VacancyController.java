package com.example.employeeoffice.controller;

import com.example.employeeoffice.service.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor

public class VacancyController {
    private final VacancyService vacancyService;

    @DeleteMapping("/delete_vacancy/{vacancyId}")
    public void deleteVacancyById(@PathVariable UUID vacancyId) {

        vacancyService.deleteVacancyById(vacancyId);
    }

}
