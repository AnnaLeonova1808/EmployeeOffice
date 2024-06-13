package com.example.employeeoffice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * Data Transfer Object for vacancy details after creation.
 */
@Data
@Schema(description = "Details of a vacancy after creation")
public class VacancyAfterCreationDto {

    /**
     * Vacancy.class
     */

    private String vacancyId;
    private String vacancyStatus = "VACANCY CREATED";

}
