package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different department names within a company.
 */
@Schema(description = "Names of departments within a company")
public enum DepartmentName {
    /**
     * IT department.
     */
    @Schema(description = "IT department")
    IT,

    /**
     * Warehouse department.
     */
    @Schema(description = "Warehouse department")
    WAREHOUSE,

    /**
     * Sales department.
     */
    @Schema(description = "Sales department")
    SALES,

    /**
     * Finance department.
     */
    @Schema(description = "Finance department")
    FINANCE,

    /**
     * Human Resources (HR) department.
     */
    @Schema(description = "Human Resources (HR) department")
    HR;
}
