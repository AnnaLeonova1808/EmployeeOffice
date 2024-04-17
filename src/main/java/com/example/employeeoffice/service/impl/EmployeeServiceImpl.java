package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.exception.EmployeeNotExistException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.EmployeeRepository;
import com.example.employeeoffice.service.interfaces.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
        return employee;
    }

    @Override
    @Transactional
    public String deleteEmployeeById(UUID empId) {
        Employee employee = employeeRepository.getEmployeeByEmpId(empId);
        if (employee!=null) {
            employeeRepository.deleteById(empId);
            return "***EMPLOYEE IS DELETED***";
        } else {
            throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);
        }
        //employeeRepository.deleteById(empId);

    }
}

