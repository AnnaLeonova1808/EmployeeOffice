package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ErrorExtension;
import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.WorkSchedule;
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
        summary = "Show workSchedule by name",
        description = "Retrieve a workSchedule by its unique name",
        tags = {"WORK_SCHEDULE"},
        parameters = {
                @Parameter(
                        name = "schedName",
                        description = "The workSchedule name",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string"),
                        examples = {
                                @ExampleObject(
                                        name = "Request with correct workSchedule name",
                                        value = "FIVE_DAY_SHIFT_08_17"
                                ),
                                @ExampleObject(
                                        name = "Request with non-exist workSchedule name",
                                        value = "SIX_DAY_SHIFT_09_16"
                                )
                        }
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "The workSchedule found and returned",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = WorkSchedule.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "The workSchedule not found ",
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
public @interface ShowWorkScheduleByName {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
