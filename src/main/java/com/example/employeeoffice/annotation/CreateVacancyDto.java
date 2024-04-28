package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Vacanсy;
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
        summary = "Create a new vacancy",
        description = "Creation of a new vacancy and return",
        tags = {"VACANCY"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The vacancy to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Vacanсy.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                    "position": "PROGRAMMER",
                                                    "vacancyDescription": "Programmer P D",
                                                    "vacancyRequirements": "Requirements for Programmer Position",
                                                    "vacancyContactInfo": "contact@example.com",
                                                    "depName": "IT"
                                                }"""
                                ),
                                @ExampleObject(name = "Existing name and description",
                                        value = """
                                                {
                                                    "position": "PROGRAMMER",
                                                    "vacancyDescription": "Receptionist Position Description",
                                                    "vacancyRequirements": "Requirements for Programmer Position",
                                                    "vacancyContactInfo": "contact@example.com",
                                                    "depName": "IT"
                                                }"""
                                ),
                                @ExampleObject(name = "Skipped fields",
                                        value = """
                                                {
                                                    "position": "PROGRAMMER",
                                                    "vacancyDescription": "Receptionist Position Description",
                                                    "vacancyRequirements": "Requirements for Programmer Position",
                                                    "vacancyContactInfo": "contact@example.com",
                                                }"""
                                ),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "The vacancy created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Vacanсy.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Possible answers:" +
                                "<br> - A vacancy with the same name and description already exists" +
                                "<br> - Please fill in all fields",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Vacanсy.class)
                        )
                )
        }
)
public @interface CreateVacancyDto {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
