package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different workplace locations.
 */
@Schema(description = "Different workplace locations")
public enum WorkPlaceLocation {
    /**
     * Office location.
     */
    @Schema(description = "Office location")
    OFFICE,

    /**
     * Warehouse location.
     */
    @Schema(description = "Warehouse location")
    WAREHOUSE,

    /**
     * Sales agent route location.
     */
    @Schema(description = "Sales agent route location")
    SALES_AGENT_ROUTE;
}
