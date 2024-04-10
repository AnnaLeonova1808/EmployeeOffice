package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.repositiry.EmployeeRepositoty;
import com.example.employeeoffice.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepositoty employeeRepositoty;
    @Override
    public Employee getEmployeeById(UUID id) {

        return employeeRepositoty.getEmployeeByEmpId(id);
    }
}
