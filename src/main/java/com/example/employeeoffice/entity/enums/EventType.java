package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different types of events within the company.
 */
@Schema(description = "Types of events within the company")
public enum EventType {
    /**
     * Conference event.
     */
    @Schema(description = "Conference event")
    CONFERENCE,

    /**
     * Seminar event.
     */
    @Schema(description = "Seminar event")
    SEMINAR,

    /**
     * Holiday event.
     */
    @Schema(description = "Holiday event")
    HOLIDAY,

    /**
     * Unpaid event.
     */
    @Schema(description = "Unpaid event")
    UNPAID,

    /**
     * Employee's birthday event.
     */
    @Schema(description = "Employee's birthday event")
    EMPLOYEE_BIRTHDAY,

    /**
     * Training event.
     */
    @Schema(description = "Training event")
    TRAINING,

    /**
     * Survey event.
     */
    @Schema(description = "Survey event")
    SURVEY;
}
