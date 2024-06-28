package com.example.employeeoffice.annotation;

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
@RequestMapping(method = RequestMethod.PUT)
@Operation(
        summary = "Update  salary by id",
        description = "The request includes updating the salary field and returning the updated personal information.",
        tags = {"PERSONAL_INFO"},
        parameters = {
                @Parameter(
                        name = "persInfoId",
                        description = "The unique identifier of the personal info",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(format = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Good Id",
                                        value = "1f486486-97dc-4f50-8fb1-cd87d5dd37e1"
                                )
                        }
                )
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Request to update personal info salary",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UpdatePersonalInfo.class),
                        examples = {
                                @ExampleObject(
                                        name = "Update salary request",
                                        value = """
                                                {
                                                    "salary": 55000.00
                                                }
                                                """
                                )
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Personal info updated successfully",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = PersonalInfo.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Personal info not found",
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
public @interface UpdatePersonalInfo {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
