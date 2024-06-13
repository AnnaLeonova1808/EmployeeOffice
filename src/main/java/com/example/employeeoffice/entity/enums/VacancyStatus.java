package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing the status of a vacancy.
 */
@Schema(description = "Status of the vacancy")
public enum VacancyStatus {
    /**
     * Vacancy is active and open.
     */
    @Schema(description = "Vacancy is active and open")
    ACTIVE,

    /**
     * Vacancy is closed and not available.
     */
    @Schema(description = "Vacancy is closed and not available")
    CLOSE;
}
