package com.example.employeeoffice.service.impl;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.*;
import com.example.employeeoffice.repository.DepartmentRepository;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<String> showAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        if (departmentList.isEmpty()) throw new ListOfDepartmentIsEmptyException(ErrorMessage.LIST_OF_DEPARTMENT_IS_EMPTY);
        return departmentList.stream()
                .map(Department::getDepName)
                .map(DepartmentName::name)
                .collect(Collectors.toSet());
    }

}