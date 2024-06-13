package com.example.employeeoffice.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enum representing different work schedule names.
 */
@Schema(description = "Different work schedule names")
public enum WorkScheduleName {
    /**
     * Five-day shift from 08:00 to 17:00.
     */
    @Schema(description = "Five-day shift from 08:00 to 17:00")
    FIVE_DAY_SHIFT_08_17,

    /**
     * Five-day shift from 09:00 to 18:00.
     */
    @Schema(description = "Five-day shift from 09:00 to 18:00")
    FIVE_DAY_SHIFT_09_18,

    /**
     * Shift work.
     */
    @Schema(description = "Shift work")
    SHIFT_WORK;
}
