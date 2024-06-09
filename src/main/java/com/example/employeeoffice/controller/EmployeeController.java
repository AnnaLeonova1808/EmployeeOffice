package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEmployee;
import com.example.employeeoffice.annotation.DeleteEmployee;
import com.example.employeeoffice.annotation.GetEmployeeById;
import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
/**
 * Controller for managing employees.
 */
@Validated
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Retrieves an employee by their ID.
     *
     * @param empId the ID of the employee to retrieve
     * @return the employee with the specified ID
     */

    //@PreAuthorize("hasRole('ADMIN')")
    @GetEmployeeById(path = "/get/{empId}")
    public Employee getEmployeeById(@PathVariable(name = "empId") @UuidFormatChecker String empId) {

        return employeeService.getEmployeeById(UUID.fromString(empId));

    }

    /**
     * Deletes an employee by their ID.
     *
     * @param empId the ID of the employee to delete
     * @return a message indicating the result of the deletion
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteEmployee(path = "/delete/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployeeById(@PathVariable @UuidFormatChecker String empId) {

        return employeeService.deleteEmployeeById(UUID.fromString(empId));

    }

    /**
     * Creates a new employee.
     *
     * @param employeeRegistrationDto the details of the employee to create
     * @return the created employee details
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @CreateEmployee(path = "/registration/create_employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeAfterRegistrationDto createEmployee(@Validated @RequestBody EmployeeRegistrationDto employeeRegistrationDto) {

        return employeeService.createEmployee(employeeRegistrationDto);

    }
}
