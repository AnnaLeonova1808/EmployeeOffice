package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Employee;

import java.util.UUID;

public interface EmployeeService {
    Employee getEmployeeById(UUID empId);
}
