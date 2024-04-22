package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.enums.Position;
import lombok.Value;

@Value
public class VacancyCreateDto {

    Position position;

    String vacancyDescription;

    String vacancyRequirements;

    String vacancyContactInfo;

    String depName;

}
