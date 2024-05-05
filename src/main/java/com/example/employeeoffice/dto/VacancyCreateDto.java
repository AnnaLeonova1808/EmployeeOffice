package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.enums.Position;
import lombok.Data;
import lombok.Value;

@Data
public class VacancyCreateDto {

    Position position;

    String vacancyDescription;

    String vacancyRequirements;

    String vacancyContactInfo;

    String depName;

}
