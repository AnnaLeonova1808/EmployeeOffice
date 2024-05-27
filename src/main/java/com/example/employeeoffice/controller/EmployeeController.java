package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreateEmployee;
import com.example.employeeoffice.annotation.DeleteEmployee;
import com.example.employeeoffice.annotation.GetEmployeeById;
import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetEmployeeById(path = "/get/{empId}")
    public Employee getEmployeeById(@PathVariable(name = "empId") @UuidFormatChecker String empId) {
        return employeeService.getEmployeeById(UUID.fromString(empId));
    }

    @DeleteEmployee(path = "/delete/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployeeById(@PathVariable @UuidFormatChecker String empId) {

        return employeeService.deleteEmployeeById(UUID.fromString(empId));
    }

    @CreateEmployee(path = "/registration/create_employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeAfterRegistrationDto createEmployee(@Validated @RequestBody EmployeeRegistrationDto employeeRegistrationDto) {

        return employeeService.createEmployee(employeeRegistrationDto);
    }
}
