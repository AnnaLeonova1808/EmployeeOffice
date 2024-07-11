package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateVacancyDto;
import com.example.employeeoffice.annotation.DeleteVacancy;
import com.example.employeeoffice.annotation.ShowAllDepartments;
import com.example.employeeoffice.annotation.ShowAllVacancies;
import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Vacancy;
import com.example.employeeoffice.service.interfaces.VacancyService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Controller for managing vacancies.
 */
@Validated
@RestController
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    /**
     * Deletes a vacancy by its ID.
     *
     * @param vacancyId the ID of the vacancy to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteVacancy(path = "/delete_vacancy/{vacancyId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteVacancyById(@PathVariable(name = "vacancyId") @UuidFormatChecker String vacancyId) {

        return vacancyService.deleteVacancyById(UUID.fromString(vacancyId));
    }

    /**
     * Creates a new vacancy.
     *
     * @param vacancyCreateDto the details of the vacancy to create
     * @return the created vacancy details
     */
    @CreateVacancyDto(path = "/create_vacancy")
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyAfterCreationDto creteVacancy(@RequestBody VacancyCreateDto vacancyCreateDto) {

        return vacancyService.createVacancy(vacancyCreateDto);
    }

    /**
     * Retrieves all vacancies.
     *
     * @return a list of all vacancies
     */
    @ShowAllVacancies(path = "/showAll")
    public ResponseEntity<Set<String>> showAllVacancies() {
        Set<String> vacancies = vacancyService.showAllVacancies();
        if (vacancies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vacancies, HttpStatus.OK);
    }
}
