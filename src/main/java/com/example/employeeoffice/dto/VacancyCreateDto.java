package com.example.employeeoffice.dto;

import com.example.employeeoffice.entity.enums.Position;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Value;
/**
 * Data Transfer Object for creating a new vacancy.
 */
@Data
@Schema(description = "Details required for creating a new vacancy")
public class VacancyCreateDto {

    /**
     * Vacancy.class
     */

    Position position;

    String vacancyDescription;

    String vacancyRequirements;

    String vacancyContactInfo;

    String depName;

}
