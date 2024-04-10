package com.example.employeeoffice.service;

import com.example.employeeoffice.entity.Employee;

import java.util.UUID;

public interface EmployeeService {
    Employee getEmployeeById(UUID id);
}
