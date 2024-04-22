package com.example.employeeoffice.dto;

import lombok.Data;

@Data
public class VacancyAfterCreationDto {

    private String vacancyId;

    private String vacancyStatus = "VACANCY CREATED";

}
