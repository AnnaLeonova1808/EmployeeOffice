package com.example.employeeoffice.controller;

import com.example.employeeoffice.annotation.GetAllRolesNames;
import com.example.employeeoffice.annotation.GetRole;
import com.example.employeeoffice.entity.Role;
import com.example.employeeoffice.exception.RoleIdNotFoundException;
import com.example.employeeoffice.service.interfaces.RoleService;
import com.example.employeeoffice.validation.annotation.UuidFormatChecker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    //@PreAuthorize("hasRole('ADMIN')")
    @GetRole(path = "/get/{role_id}")
    public Role getRoleById(@PathVariable(name = "role_id") @UuidFormatChecker String roleId){

        return roleService.getRoleById(UUID.fromString(roleId));

    }
    /**
     * Retrieves all role names.
     *
     * @return a set of all role names
     */
    //@PreAuthorize("hasRole('ADMIN')")
    @GetAllRolesNames(path = "/rolesNames")
    public Set<String> getAllRolesNames() {

        return roleService.getAllRoleNames();

    }
}
