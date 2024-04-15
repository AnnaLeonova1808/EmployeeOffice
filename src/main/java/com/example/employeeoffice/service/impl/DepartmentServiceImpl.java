package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repositiry.DepartmentRepository;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department showDepartmentByName(DepartmentName depName) {
        Department department = departmentRepository.findByDepName(depName);
        if (department == null) {
            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);
        }
        return department;

    }
}