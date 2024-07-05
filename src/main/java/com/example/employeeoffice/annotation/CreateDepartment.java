package com.example.employeeoffice.annotation;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Event;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
        summary = "Create a new department",
        description = "Creation of a new department and return",
        tags = {"DEPARTMENT"},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "The department to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Department.class),
                        examples = {
                                @ExampleObject(name = "Good request",
                                        value = """
                                                {
                                                 "depId": "2e88a78d-b4a7-4a00-b590-4d0f7abe6c05",
                                                 "depName": "MARKETING",
                                                 "depPhone": "+12-345-678-90-27",
                                                 "depEmail": "marketing@example.com",
                                                 "depManager": {
                                                 "empId": "55035fe9-37e3-466f-ba4a-197f23fc5700"
                                                },
                                                 "vacancies": []                                            
                                                }"""),
                                @ExampleObject(name = "Request with existing name",
                                        value = """
                                                {
                                                    "depId": "2e88a78d-b4a7-4a00-b590-4d0f7abe6c04",
                                                    "depName": "SALES",
                                                    "depPhone": "+12-345-678-90-23",
                                                    "depEmail": "sales@example.com",
                                                    "depManager": {
                                                        "empId": "55035fe9-37e3-466f-ba4a-197f23fc5700"
                                                    }
                                                }"""),
                        }
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "The department created",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Department.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "409",
                        description = "The department already exist",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Department.class)
                        )
                )
        }
)
public @interface CreateDepartment {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
