package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping(method = RequestMethod.GET)
@Operation(
        summary = "Show role by ID",
        description = "Retrieve an role by its unique identifier",
        tags = {"ROLE"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the role",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with correct Id",
                                        value = "64d1e267-7034-4c72-989b-0e3214f264ce"
                                ),
                                @ExampleObject(
                                        name = "Example request with non-exist Id",
                                        value = "44d1e267-7034-4c72-989b-0e3214f264ce"
                                ),
                                @ExampleObject(
                                        name = "Example request with invalid Id",
                                        value = "64d1e267-7034-4c72-989b-0e3214f264ce55"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Role found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Role.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ), @ApiResponse(
                responseCode = "404",
                description = "Role not found",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema( implementation = ResponseExceptionHandler.class)
                )
        )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface GetRole {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
