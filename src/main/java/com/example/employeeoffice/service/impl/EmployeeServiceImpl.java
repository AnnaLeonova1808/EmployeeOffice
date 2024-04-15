package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.exception.EmployeeNotExistException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repositiry.EmployeeRepository;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(UUID empId) {
        Employee employee = employeeRepository.getEmployeeByEmpId(empId);
        if (employee == null) {
            throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);

        }
        return employeeRepository.getEmployeeByEmpId(empId);
    }
}
