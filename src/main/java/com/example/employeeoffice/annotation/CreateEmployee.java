package com.example.employeeoffice.annotation;

import com.example.employeeoffice.controller.handler.ResponseExceptionHandler;
import com.example.employeeoffice.entity.Employee;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Create new employee",
        description = "Create new employee and return him",
        tags = {"EMPLOYEE"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The employee to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Employee.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                  "firstName": "Mary",
                                                  "lastName": "Doe",
                                                  "position": "SALES_MANAGER",
                                                  "hireDate": "2024-04-23",
                                                  "createdAt": "2024-04-23T10:00:00Z",
                                                  "department": "SALES",
                                                  "birthday": "1990-01-01",
                                                  "username": "marydoe",
                                                  "phoneNumber": "+37-150-111-22-33",
                                                  "email": "marydoe@example.com",
                                                  "password": "securepassword",
                                                  "salary": 50000.0,
                                                  "employeeStatus": "EMPLOYEE CREATED"
                                                }
                                                """),
                                @ExampleObject(name = "Request with existing name",
                                        value = """
                                                {
                                                "firstName" : "Olivia",
                                                "lastName" : "Martinez",
                                                "position" : "PROGRAMMER",
                                                "hireDate" : "2022-01-15",
                                                "createdAt" : "2024-03-23T10:00:00Z",
                                                "department" : "IT",
                                                }"""),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Employee created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Employee.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Employee already exist",
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
public @interface CreateEmployee {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
