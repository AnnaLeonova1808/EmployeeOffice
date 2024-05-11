package com.example.employeeoffice.controller;

import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/get/{role_id}")
    public Role getRoleById(@PathVariable("role_id") @UuidFormatChecker String roleId) {
        return roleService.getRoleById(UUID.fromString(roleId));
    }

    @GetMapping("/roles/")
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }


}
