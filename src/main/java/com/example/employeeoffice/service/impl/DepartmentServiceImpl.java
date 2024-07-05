package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.DepartmentWithTheSameNameAlreadyExistsException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Department addDepartment(Department department) {
        Department check = departmentRepository.findByDepName(department.getDepName());
        if (check != null) throw new DepartmentWithTheSameNameAlreadyExistsException
                (ErrorMessage.DEPARTMENT_ALREADY_EXIST);

        return departmentRepository.saveAndFlush(department);
    }
}