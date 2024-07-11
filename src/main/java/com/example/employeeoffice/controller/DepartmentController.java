package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowAllDepartments;
import com.example.employeeoffice.annotation.ShowDepartment;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.exception.DepartmentNotFoundException;
import com.example.employeeoffice.exception.ErrorMessage;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
/**
 * REST controller for managing departments.
 * <p>
 * This controller provides endpoints for retrieving a department by name, adding a new department,
 * and retrieving all department names. It uses {@link DepartmentService} for department-related operations.
 * The controller is mapped to the "/department" URL path.
 */

 @RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    /**
     * Retrieves a department by its name.
     *
     * @param depName The name of the department.
     * @return The department with the specified name.
     * @throws DepartmentNotFoundException if the department does not exist.
     */

//GET http://localhost:8080/department/show_department_by_name/IT
    @ShowDepartment(path = "/show_department_by_name/{depName}")
    public Department showDepartmentByName(@PathVariable(name = "depName") String depName) {

        try {
            return departmentService.showDepartmentByName(DepartmentName.valueOf(depName));

        } catch (IllegalArgumentException e) {

            throw new DepartmentNotFoundException(ErrorMessage.DEPARTMENT_NOT_EXIST);
        }
    }

    /**
     * Retrieves all department names.
     *
     * @return A set of all department names.
     */
    @ShowAllDepartments(path = "/showAll")
    public Set<String> showAllDepartments() {

        return departmentService.showAllDepartment();
    }
}


