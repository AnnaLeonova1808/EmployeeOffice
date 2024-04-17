package com.example.employeeoffice.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vacancy")
@RequiredArgsConstructor
public class VacancyController {
    private final VacancyServiceDto vacancyServiceDto;

    @PostMapping("create")
    public VacancyAfterCreationDto createVacancy(@RequestBody VacancyCreateDto vacancyCreateDto){
        return vacancyServiceDto.createVacancy(vacancyCreateDto);
    }
}
