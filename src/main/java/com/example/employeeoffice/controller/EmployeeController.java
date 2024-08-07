package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.*;
import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.WorkSchedule;
import com.example.employeeoffice.entity.enums.WorkScheduleName;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @CreateEmployee(path = "/registration/create_employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeAfterRegistrationDto createEmployee(@Validated @RequestBody EmployeeRegistrationDto employeeRegistrationDto) {

        return employeeService.createEmployee(employeeRegistrationDto);

    }

    /**
     * Retrieves a list of employees by city.
     *
     * @param workScheduleName the workSchedule to filter employees by
     * @return the list of employees working in the specified workSchedule
     */

    @GetAllEmployeeByWorkSchedule(path = "/allEmployeeByWorkSchedule/{workScheduleName}")
    public List<Employee> getAllEmployeeByWorkSchedule(@PathVariable(name = "workScheduleName") WorkScheduleName workScheduleName) {
        return employeeService.getAllEmployeeByWorkSchedule(workScheduleName);

    }

    /**
     * Endpoint to retrieve a list of all employees.
     *
     * This method handles HTTP GET requests to the "/showAll" endpoint.
     * It retrieves a list of all employees from the employee service.
     *
     * @return a list of {@link Employee} objects representing all employees.
     */
    @ShowAllEmployee(path = "/showAll")
    public List<Employee> showAllEmployees(){
        return employeeService.showAllEmployees();
    }

}
