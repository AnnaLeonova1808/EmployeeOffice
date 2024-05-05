package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowDepartment;
import com.example.employeeoffice.entity.Department;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.entity.enums.DepartmentName;
import com.example.employeeoffice.service.interfaces.DepartmentService;
import com.example.employeeoffice.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/get/{role_id}")
    public Role getRoleById(@PathVariable("role_id") UUID roleId){
        return roleService.getRoleById(roleId);
    }

    @GetMapping("/roles/")
    public List<Role> getRoles(){
        return roleService.getAllRoles();
    }

    @RestController
    @RequestMapping("/department")
    @RequiredArgsConstructor

    public static class DepartmentController {
        private final DepartmentService departmentService;

        @ShowDepartment(path = "/show_department_by_name/{depName}")
        public Department showDepartmentByName(@PathVariable(name = "depName") String depName) {
            DepartmentName departmentName = DepartmentName.valueOf(depName.toUpperCase());
            return departmentService.showDepartmentByName(departmentName);

        }
    }
}
