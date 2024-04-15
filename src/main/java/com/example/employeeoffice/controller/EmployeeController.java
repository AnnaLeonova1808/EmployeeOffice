package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get/{empId}")
    public Employee getEmployeeById(@PathVariable(name = "empId") UUID empId) {
        return employeeService.getEmployeeById(empId);
    }

}
