package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ErrorExtension;
import com.example.employeeoffice.entity.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.ErrorResponse;
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
        summary = "Show all employees by workSchedule",
        description = "Get a list of employees with particular workSchedule",
        tags = {"EMPLOYEE"},
        parameters = {
                @Parameter(
                        name = "workScheduleName",
                        description = "WorkSchedule",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(
                                type = "string"
                        ),
                        examples = {
                                @ExampleObject(
                                        name = "Existing workSchedule 08-17",
                                        value = "FIVE_DAY_SHIFT_08_17"
                                ),
                                @ExampleObject(
                                        name = "Existing workSchedule 09-18",
                                        value = "FIVE_DAY_SHIFT_09_18"
                                ),
                                @ExampleObject(
                                        name = "Existing workSchedule Shift Work",
                                        value = "SHIFT_WORK"
                                ),
                                @ExampleObject(
                                        name = "Non-existing workSchedule",
                                        value = "FOUR_DAY_SHIFT_08_17"
                                )
                        }
                )
        },

        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Required employees received or no employees found",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Employee.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "No employees found for the given work schedule",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ErrorExtension.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid work schedule",
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
public @interface GetAllEmployeeByWorkSchedule {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
