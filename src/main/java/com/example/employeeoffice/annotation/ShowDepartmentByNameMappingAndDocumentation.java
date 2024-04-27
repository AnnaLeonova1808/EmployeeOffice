package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Department;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
        summary = "Show department by name",
        description = "Retrieve a department by its unique name",
        tags = {"DEPARTMENT"},
        parameters = {
                @Parameter(
                        name = "name",
                        description = "The department name",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "String")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Department found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Department.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Department not found"
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID"
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        },
        hidden = false
)
public @interface ShowDepartmentByNameMappingAndDocumentation {
        @AliasFor(annotation = RequestMapping.class, attribute = "path")
        String[] path() default {};
}
