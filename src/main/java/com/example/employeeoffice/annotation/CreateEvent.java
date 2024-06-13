package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create a new event",
        description = "Creation of a new event and return",
        tags = {"EVENT"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The event to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Event.class),
                        examples= {
                        @ExampleObject(name = "Good request",
                                value = """
                                        {
                                            "evType": "CONFERENCE",
                                            "startDateTime": "2024-05-20T10:00:00",
                                            "location": "Conference Hall A",
                                            "description": "Annual company conference"
                                        }"""),
                        @ExampleObject(name = "Request with existing name",
                                value = """
                                        {
                                            "evType": "TRAINING",
                                            "startDateTime": "2024-07-15 10:00:00",
                                            "location": "Training Center",
                                            "description": "New employee orientation"
                                        }"""),
                       }
                )
        ),
        responses={
        @ApiResponse(
                responseCode = "201",
                description = "The event created",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Event.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "The event already exist",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Event.class)
                )
        )
}
)
public @interface CreateEvent {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
