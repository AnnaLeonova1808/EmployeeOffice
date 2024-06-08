package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.Authority;
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

/**
 * Annotation to specify the retrieval of an authority by ID.
 * Аннотация для указания получения права по его идентификатору.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.GET)
@Operation(
        summary = "Show authority by ID",
        description = "Retrieve an user by its unique identifier",
        tags = {"AUTHORITY"},
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique identifier of the authority",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with correct Id",
                                        value = "fcbf07ae-7d86-4a16-935a-36ee7ac89b02"
                                ),
                                @ExampleObject(
                                        name = "Example request with non-exist Id",
                                        value = "fcbf07ae-7d86-4a16-935a-36ee7ac89b03"
                                ),
                                @ExampleObject(
                                        name = "Example request with invalid Id",
                                        value = "d234d99d-!70e-42f7-aa6ae-435ee56f49b5"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Authority found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Authority.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "Authority not found",
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

public @interface GetAuthority {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
