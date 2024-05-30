package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different statuses of an employee within the company.
 */
@Schema(description = "Different statuses of an employee within the company")
public enum StatusEmployee {
    /**
     * Employee is currently working.
     */
    @Schema(description = "Employee is currently working")
    WORK,

    /**
     * Employee is on vacation.
     */
    @Schema(description = "Employee is on vacation")
    VACATION,

    /**
     * Employee is on sick leave.
     */
    @Schema(description = "Employee is on sick leave")
    SICK_LEAVE;
}
