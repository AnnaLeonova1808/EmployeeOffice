package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.CreatEmployee;
import com.example.employeeoffice.annotation.DeleteEmployee;
import com.example.employeeoffice.annotation.GetEmployeeById;
import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetEmployeeById(path = "/get/{empId}")
    public Employee getEmployeeById(@PathVariable(name = "empId") UUID empId) {
        return employeeService.getEmployeeById(empId);
    }

    @DeleteEmployee(path = "/delete/{empId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteEmployeeById(@PathVariable UUID empId) {

        return employeeService.deleteEmployeeById(empId);
    }
    @CreatEmployee(path = "/registration/create_employee")
    public EmployeeAfterRegistrationDto creatEmployee(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
        return employeeService.createEmployee(employeeRegistrationDto);
    }
}
