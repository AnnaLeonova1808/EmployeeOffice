package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAllRolesNames;
import com.example.employeeoffice.annotation.GetRole;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.exception.RoleIdNotFoundException;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetRole(path = "/get/{role_id}")
    public Role getRoleById(@PathVariable("role_id") @UuidFormatChecker String roleId){

        return roleService.getRoleById(UUID.fromString(roleId));
    }

    //@GetMapping(path = "/rolesNames")
    @GetAllRolesNames(path = "/rolesNames")
    public Set<String> getAllRolesNames() {
        return roleService.getAllRoleNames();
    }
}
