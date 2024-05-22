package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ErrorExtension;
import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.PersonalInfo;
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
        summary = "Show all personals info by role name",
        description = "Get a list of personals info with particular role name",
        tags = {"PERSONAL_INFO"},
        parameters = {
                @Parameter(
                        name = "Role name",
                        description = "Role name",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Existing Role name",
                                        value = "ROLE_MANAGER"
                                ),
                                @ExampleObject(
                                        name = "Non-existing Role name",
                                        value = "MANAGER"
                                ),
                                @ExampleObject(
                                        name = "Invalid Role name",
                                        value = "INVALID_ROLE"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Required personals info received",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PersonalInfo.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid Role name",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Role name not found or no personals info found for the role",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseExceptionHandler.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)

public @interface GetPersonalInfoByRoleName {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
