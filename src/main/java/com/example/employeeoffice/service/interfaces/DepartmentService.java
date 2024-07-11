package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;

import java.util.List;
import java.util.Set;

public interface DepartmentService {
    Department showDepartmentByName (DepartmentName depName);

    Set<String> showAllDepartment();
}
