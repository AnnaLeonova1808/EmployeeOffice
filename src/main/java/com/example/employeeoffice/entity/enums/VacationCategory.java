package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different categories of vacation.
 */
@Schema(description = "Categories of vacation")
public enum VacationCategory {
    /**
     * Annual vacation.
     */
    @Schema(description = "Annual vacation")
    ANNUAL,

    /**
     * Maternity leave.
     */
    @Schema(description = "Maternity leave")
    MATERNITY,

    /**
     * Parental leave.
     */
    @Schema(description = "Parental leave")
    PARENTAL,

    /**
     * Unpaid leave.
     */
    @Schema(description = "Unpaid leave")
    UNPAID,

    /**
     * Other types of vacation.
     */
    @Schema(description = "Other types of vacation")
    OTHER;
}
