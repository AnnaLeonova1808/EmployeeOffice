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
       if (employee == null){
           throw new EmployeeNotExistException(ErrorMessage.EMPLOYEE_NOT_EXIST);

       }

        return employeeRepository.getEmployeeByEmpId(empId);
    }

//    @Transactional
//    public String createEmployee(String name) {
//        Employee employee = new Employee();
//        employee.setFirstName(name);
//        employee.setDepartment(new Department());
//
//        employeeRepository.saveAndFlush(employee);
//        UUID result = employeeRepository.findAllById(name);
//        return String.valueOf(result);
//    }
//
//    @Override
//    public Employee updateEmployee(Employee employee, Employee newEmployee) {
//        return null;
//    }
//
//    @Override
//    public Employee deleteEmployee(UUID id) {
//        return null;
//    }
}
