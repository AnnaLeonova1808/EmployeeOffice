package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable("id") UUID id) {
        return employeeService.getEmployeeById(id);
    }
}
