package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.ShowAllRolesNames;
import com.example.employeeoffice.annotation.GetRole;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

/**
 * Controller for managing roles.
 */
@Validated
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    /**
     * Retrieves a role by its ID.
     *
     * @param roleId the ID of the role to retrieve
     * @return the role with the specified ID
     */
    @GetRole(path = "/get/{role_id}")
    public Role getRoleById(@PathVariable(name = "role_id") @UuidFormatChecker String roleId){

        return roleService.getRoleById(UUID.fromString(roleId));

    }
    /**
     * Retrieves all role names.
     *
     * @return a set of all role names
     */
    @ShowAllRolesNames(path = "/rolesNames")
    public Set<String> getAllRolesNames() {

        return roleService.getAllRoleNames();

    }
}
