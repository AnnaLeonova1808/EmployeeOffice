package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.Address;
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
            summary = "Update  address by id",
            description = "The request includes updating the address .",
            tags = {"ADDRESS"},
            parameters = {
                    @Parameter(
                            name = "addressId",
                            description = "The unique identifier of the address",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(format = "string"),
                            examples = {
                                    @ExampleObject(
                                            name = "Good Id",
                                            value = "0b751135-128c-46c9-b554-8c6e05bcd2d8"
                                    )
                            }
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request to update address",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = com.example.employeeoffice.annotation.UpdateAddress.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Update apartNumber, houseNumber, city, street request",
                                            value = """
                                                    {
                                                        "addressType": "HOME",
                                                        "apartNumber": "111",
                                                        "houseNumber": "321",
                                                        "street": "Hauptstrasse",
                                                        "city": "Berlin"
                                                    }
                                                    """
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Address updated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Address.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Address not found",
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
    public @interface UpdateAddress {
        @AliasFor(annotation = RequestMapping.class, attribute = "path")
        String[] path() default {};
    }

