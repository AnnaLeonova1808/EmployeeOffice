package com.example.employeeoffice.annotation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping(method = RequestMethod.DELETE)
@Operation(
        summary = "Delete vacancy by ID",
        description = "Delete an existing vacancy by its unique identifier",
        tags = {"VACANCY"},
        parameters = {
                @Parameter(
                        name = "vacancyId",
                        description = "The unique identifier of the vacancy",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid"),
                        examples = {
                                @ExampleObject(
                                        name = "Example existing Id",
                                        value = "51b02a7e-e57c-4321-ba34-73d59bfbddec"
                                ),
                                @ExampleObject(
                                        name = "Example non-existing Id",
                                        value = "efff9467-a80e-447d-8763-ee7acfa5cc"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "The vacancy deleted"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "The vacancy does not exist"
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface DeleteVacancy {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
