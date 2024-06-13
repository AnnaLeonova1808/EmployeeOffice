package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing types of addresses.
 */
@Schema(description = "Types of addresses")
public enum AddressType {
    /**
     * Home address type.
     */
    @Schema(description = "Home address")
    HOME,

    /**
     * Work address type.
     */
    @Schema(description = "Work address")
    WORK;
}
