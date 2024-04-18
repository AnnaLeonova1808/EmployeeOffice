package com.example.employeeoffice.controller;

import com.example.employeeoffice.dto.VacancyAfterCreationDto;
import com.example.employeeoffice.dto.VacancyCreateDto;
import com.example.employeeoffice.service.interfaces.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create_vacancy")
    public VacancyAfterCreationDto creteVacancy(@RequestBody VacancyCreateDto vacancyCreateDto){
        return vacancyService.createVacancy(vacancyCreateDto);
    }

}
