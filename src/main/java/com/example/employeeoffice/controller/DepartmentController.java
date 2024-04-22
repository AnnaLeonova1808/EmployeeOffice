package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor

public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/show_department_by_name/{depName}")
    public Department showDepartmentByName(@PathVariable(name = "depName") String depName) {
        //return departmentService.showDepartmentByName(depName);
        DepartmentName departmentName = DepartmentName.valueOf(depName.toUpperCase());
        return departmentService.showDepartmentByName(departmentName);

    }
}