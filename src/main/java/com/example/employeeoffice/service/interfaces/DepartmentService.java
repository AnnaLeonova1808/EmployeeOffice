package com.example.employeeoffice.service.interfaces;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;

public interface DepartmentService {
    Department showDepartmentByName (DepartmentName depName);

    Department addDepartment(Department department);


}
