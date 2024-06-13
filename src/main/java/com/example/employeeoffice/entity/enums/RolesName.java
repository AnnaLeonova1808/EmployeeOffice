package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different role names within the company.
 */
@Schema(description = "Different role names within the company")
public enum RolesName  {
    /**
     * Admin role.
     */
    @Schema(description = "Admin role")
    ROLE_ADMIN,

    /**
     * Manager role.
     */
    @Schema(description = "Manager role")
    ROLE_MANAGER,

    /**
     * User role.
     */
    @Schema(description = "User role")
    ROLE_USER,

    /**
     * Guest role.
     */
    @Schema(description = "Guest role")
    ROLE_GUEST;
}
