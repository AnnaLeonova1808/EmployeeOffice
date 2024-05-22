package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetRole;
import com.example.employeeoffice.annotation.GetAllRolesNames;
import com.example.employeeoffice.entity.Employee;
import com.example.employeeoffice.entity.PersonalInfo;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.exception.RoleNotFoundException;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetRole(path = "/get/{role_id}")
    public Role getRoleById(@PathVariable("role_id") @UuidFormatChecker String roleId) {

        return roleService.getRoleById(UUID.fromString(roleId));
    }

    @GetMapping(path ="/rolesNames")
    @GetAllRolesNames
    public Set<String> getAllRolesNames() {
        return roleService.getAllRoleNames();

    }


}
