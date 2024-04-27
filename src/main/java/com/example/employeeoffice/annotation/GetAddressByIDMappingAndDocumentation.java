package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Address;
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
        summary = "Show address by ID",
        description = "Retrieve an address by its unique identifier",
        tags = {"ADDRESS"},
        parameters = {
                @Parameter(
                        name = "addressId",
                        description = "The unique identifier of the address",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Address found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Address.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Address not found"

                                        )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        },
        hidden = false
)

public @interface GetAddressByIDMappingAndDocumentation {
        @AliasFor(annotation = RequestMapping.class, attribute = "path")
        String[] path() default {};

}
