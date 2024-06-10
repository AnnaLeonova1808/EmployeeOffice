package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowDepartment;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

//GET http://localhost:8080/department/show_department_by_name/IT
    @ShowDepartment(path = "/show_department_by_name/{depName}")
    public Department showDepartmentByName(@PathVariable(name = "depName") String depName) {

        try {
            return departmentService.showDepartmentByName(DepartmentName.valueOf(depName));

        } catch (IllegalArgumentException e) {

            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);

        }
    }
}


