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
        summary = "Show personal info by ID",
        description = "Retrieve a personal info by its unique identifier",
        tags = {"PERSONAL_INFO"},
        parameters = {
                @Parameter(
                        name = "persInfoId",
                        description = "The unique identifier of the personal info",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Example request with correct Id",
                                        value = "1f486486-97dc-4f50-8fb1-cd87d5dd37e1"
                                ),
                                @ExampleObject(
                                        name = "Example request with non-exist Id",
                                        value = "2f486486-97dc-4f50-8fb1-cd87d5dd37e1"
                                ),
                                @ExampleObject(
                                        name = "Example request with invalid Id",
                                        value = "11111111"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Personal info found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PersonalInfo.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Personal info not found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorExtension.class)
                        )

                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorExtension.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetPersonalInfo {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
