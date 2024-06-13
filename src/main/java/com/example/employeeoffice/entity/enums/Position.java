package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different positions within the company.
 */
@Schema(description = "Different positions within the company")
public enum Position {
    /**
     * Manager position.
     */
    @Schema(description = "Manager position")
    MANAGER,

    /**
     * Economist position.
     */
    @Schema(description = "Economist position")
    ECONOMIST,

    /**
     * Sales Manager position.
     */
    @Schema(description = "Sales Manager position")
    SALES_MANAGER,

    /**
     * Programmer position.
     */
    @Schema(description = "Programmer position")
    PROGRAMMER,

    /**
     * HR Manager position.
     */
    @Schema(description = "HR Manager position")
    HR_MANAGER,

    /**
     * Storekeeper position.
     */
    @Schema(description = "Storekeeper position")
    STOREKEEPER,

    /**
     * Driver position.
     */
    @Schema(description = "Driver position")
    DRIVER;
}
