package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Event;
import com.example.employeeoffice.entity.Vacancy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.ErrorResponse;
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
        summary = "Show all vacancies",
        description = "Get a list of all existing vacancy",
        tags = {"VACANCY"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All vacancies received or no vacancies found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Vacancy.class)
                        )
                ),
                        @ApiResponse(
                        responseCode = "404",
                        description = "No vacancies found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface ShowAllVacancies {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
