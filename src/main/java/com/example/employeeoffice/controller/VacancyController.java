package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.DeleteVacancy;
import com.example.employeeoffice.annotation.CreateVacancyDto;
import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.service.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@ComponentScan
@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyService vacancyService;

    @DeleteVacancy(path = "/delete_vacancy/{vacancyId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteVacancyById(@PathVariable UUID vacancyId) {

        return vacancyService.deleteVacancyById(vacancyId);
    }
    @CreateVacancyDto(path = "/create_vacancy")
    @ResponseStatus(HttpStatus.CREATED)
    public VacancyAfterCreationDto creteVacancy(@RequestBody VacancyCreateDto vacancyCreateDto){

        return vacancyService.createVacancy(vacancyCreateDto);
    }

}
