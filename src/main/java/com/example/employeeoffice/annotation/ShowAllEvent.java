package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.Event;
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
        summary = "Show all events",
        description = "Get a list of all existing event",
        tags = {"EVENT"},
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "All events received or no events found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Event.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface ShowAllEvent {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
