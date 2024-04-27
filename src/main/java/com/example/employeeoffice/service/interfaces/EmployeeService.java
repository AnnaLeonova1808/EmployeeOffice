package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.dto.EmployeeAfterRegistrationDto;
import com.example.employeeoffice.dto.EmployeeRegistrationDto;
import com.example.employeeoffice.entity.Employee;

import java.util.UUID;

public interface EmployeeService {
    Employee getEmployeeById(UUID empId);
    String deleteEmployeeById(UUID empId);
    EmployeeAfterRegistrationDto createEmployee(EmployeeRegistrationDto employeeRegistrationDto);
}
