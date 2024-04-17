package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.enums.VacancyStatus;
import lombok.Data;

@Data
public class VacancyAfterCreationDto {
    private String vacancyId;
    private String vacancyStatus = "VACANCY CREATED";
}
