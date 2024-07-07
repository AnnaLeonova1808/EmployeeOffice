package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.WorkSchedule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET)
@Operation(
        summary = "Show all workSchedules",
        description = "Get a list of all existing workSchedule",
        tags = {"WORK_SCHEDULE"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All workSchedules received or no workSchedules found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = WorkSchedule.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface ShowAllWorkSchedule {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
