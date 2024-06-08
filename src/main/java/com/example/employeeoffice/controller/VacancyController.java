package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateVacancyDto;
import com.example.employeeoffice.annotation.DeleteVacancy;
import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.service.interfaces.VacancyService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for managing vacancies.
 */
@Validated
@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    /**
     * Deletes a vacancy by its ID.
     *
     * @param vacancyId the ID of the vacancy to delete
     * @return a message indicating the result of the deletion
     */
    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @CreateVacancyDto(path = "/create_vacancy")
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyAfterCreationDto creteVacancy(@RequestBody VacancyCreateDto vacancyCreateDto) {

        return vacancyService.createVacancy(vacancyCreateDto);

    }
}
