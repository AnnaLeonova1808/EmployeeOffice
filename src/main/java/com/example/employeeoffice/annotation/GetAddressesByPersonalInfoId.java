package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.Address;
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
        summary = "Show addresses",
        description = "Retrieve all addresses by personalInfoId",
        tags = {"ADDRESS"},
        parameters ={
                @Parameter(
                        name = "persInfoId",
                        description = "The ID of the personal information to retrieve addresses for",
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
                                        value = "1f486486-97dc-4f50-8fb1-cd87d5dd37e2"
                                ),
                                @ExampleObject(
                                        name = "Example request with invalid Id",
                                        value = "invalidId"
                                )
                        }
                )
        },


        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Addresses found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(type = "array", implementation = Address.class)
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
                        responseCode = "404",
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

public @interface GetAddressesByPersonalInfoId {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}

